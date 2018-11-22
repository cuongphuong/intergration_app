package com.example.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	 @Autowired
	 @Qualifier("c_datasource3")
	 private DataSource dataSource;
	 
	@Override
	   protected void configure(HttpSecurity http) throws Exception {
	       http.csrf().disable().authorizeRequests()
	               .antMatchers("/").permitAll()
	               .antMatchers(HttpMethod.POST, "/login").permitAll() 
	               .anyRequest().authenticated()
	               .and()
	               //http://docs.spring.io/spring-security/site/docs/3.0.x/reference/security-filter-chain.html
	               .addFilterBefore(new JWTLoginFilter("/login", authenticationManager()), UsernamePasswordAuthenticationFilter.class) 
	               .addFilterBefore(new JWTAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
	   }
	
	@Override
	   protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.inMemoryAuthentication().passwordEncoder(NoOpPasswordEncoder.getInstance())
//        .withUser("admin").password("123").roles("ADMIN").and()
//        .withUser("test1").password("test123").roles("ADMIN");
		
	       auth.jdbcAuthentication().dataSource(dataSource)
	               .usersByUsernameQuery("select username,password,enabled from tb_users where username=?")
	               .authoritiesByUsernameQuery("select username, function_id as role from tb_access_control inner join tb_users on (tb_access_control.user_id=tb_users.user_id) where tb_users.username=?")
	               .passwordEncoder(new BCryptPasswordEncoder(16));
	   }
}
