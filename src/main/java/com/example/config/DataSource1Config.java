package com.example.config;

import java.util.HashMap;

import javax.sql.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@PropertySources({ @PropertySource("classpath:datasource-cfg.properties")})
@EnableJpaRepositories(
        entityManagerFactoryRef = "ds1EntityManagerFactory", 
        transactionManagerRef = "ds1TransactionManager",
        basePackages = { "com.example.repository.ds1" })
public class DataSource1Config {
	@Autowired
	private Environment env;
	
	@Bean(name="c_datasource1")
    public DataSource ds1Datasource() {
 
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("spring.datasource.driver-class-name.1"));
        dataSource.setUrl(env.getProperty("spring.datasource.url.1"));
        dataSource.setUsername(env.getProperty("spring.datasource.username.1"));
        dataSource.setPassword(env.getProperty("spring.datasource.password.1"));
 
        return dataSource;
    }
 
    @Bean(name= "ds1EntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean ds1EntityManager() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(ds1Datasource());
 
        // Scan Entities in Package:
        em.setPackagesToScan(new String[] { Constants.PACKAGE_ENTITIES_1 });
        em.setPersistenceUnitName(Constants.JPA_UNIT_NAME_1); // Important !!
 
        //
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
 
        em.setJpaVendorAdapter(vendorAdapter);
 
        HashMap<String, Object> properties = new HashMap<>();
 
        // JPA & Hibernate
        properties.put("hibernate.dialect", env.getProperty("spring.jpa.properties.hibernate.dialect.1"));
        properties.put("hibernate.show-sql", env.getProperty("spring.jpa.show-sql.1"));
 
        // Solved Error: PostGres createClob() is not yet implemented.
        // PostGres Only:
        // properties.put("hibernate.temp.use_jdbc_metadata_defaults",  false);
 
        em.setJpaPropertyMap(properties);
        em.afterPropertiesSet();
        return em;
    }
 
    @Bean(name= "ds1TransactionManager")
    public PlatformTransactionManager ds1TransactionManager() {
 
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(ds1EntityManager().getObject());
        return transactionManager;
    }
}
