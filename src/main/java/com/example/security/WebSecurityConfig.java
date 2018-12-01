package com.example.security;

import java.util.Arrays;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	@Qualifier("c_datasource3")
	private DataSource dataSource;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable().authorizeRequests().antMatchers("/").permitAll()
		.antMatchers("/home/encodehtml").permitAll()
				.antMatchers(HttpMethod.POST, "/login").permitAll().anyRequest().authenticated().and()
				.addFilterBefore(new JWTLoginFilter("/login", authenticationManager()),
						UsernamePasswordAuthenticationFilter.class)
				.addFilterBefore(new JWTAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
	}

	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
	    configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000", "*"));
	    configuration.setAllowedMethods(Arrays.asList("GET", "POST", "OPTIONS", "DELETE", "PUT", "PATCH"));
	    configuration.setAllowedHeaders(Arrays.asList( "Authorization","X-Requested-With", "Origin", "Content-Type", "Accept", "ContentType"));
	    configuration.setExposedHeaders(Arrays.asList("Authorization"));
//	    configuration.setAllowCredentials(true);
	    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	    source.registerCorsConfiguration("/**", configuration);
	    return source;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.inMemoryAuthentication().passwordEncoder(NoOpPasswordEncoder.getInstance())
//        .withUser("admin").password("123").roles("ADMIN").and()
//        .withUser("test1").password("test123").roles("ADMIN");

		auth.jdbcAuthentication().dataSource(dataSource)
				.usersByUsernameQuery("select username,password,enabled from tb_users where username=?")
				.authoritiesByUsernameQuery(
						"select username, function_id as role from tb_access_control inner join tb_users on (tb_access_control.user_id=tb_users.user_id) where tb_users.username=?")
				.passwordEncoder(new BCryptPasswordEncoder(16));
	}
}
