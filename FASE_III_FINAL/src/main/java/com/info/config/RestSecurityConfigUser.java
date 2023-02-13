package com.info.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@Order(1)
public class RestSecurityConfigUser extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.antMatcher("/api/**");
		
		// URLs that need authentication to access to it
		http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/item/items").hasAnyRole("USER", "ADMIN", "MANAGER");
		http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/item/**").hasAnyRole("USER","ADMIN", "MANAGER");
		http.authorizeRequests().antMatchers(HttpMethod.POST, "/api/item/**").hasAnyRole("MANAGER","ADMIN");
		http.authorizeRequests().antMatchers(HttpMethod.PUT, "/api/item/**").hasAnyRole("MANAGER","ADMIN");
		http.authorizeRequests().antMatchers(HttpMethod.DELETE, "/api/item/**").hasAnyRole("MANAGER","ADMIN");
		
		http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/user/users").hasAnyRole("MANAGER","ADMIN");
		http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/user/**").hasAnyRole("MANAGER","ADMIN");
		http.authorizeRequests().antMatchers(HttpMethod.POST, "/api/user/**").hasAnyRole("MANAGER","ADMIN");
		http.authorizeRequests().antMatchers(HttpMethod.PUT, "/api/user/**").hasAnyRole("MANAGER","ADMIN");
		http.authorizeRequests().antMatchers(HttpMethod.DELETE, "/api/user/**").hasAnyRole("MANAGER","ADMIN");
		
		http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/category/categories").hasAnyRole("MANAGER","ADMIN");
		http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/category/**").hasAnyRole("MANAGER","ADMIN");
		http.authorizeRequests().antMatchers(HttpMethod.POST, "/api/category/**").hasAnyRole("MANAGER","ADMIN");
		http.authorizeRequests().antMatchers(HttpMethod.PUT, "/api/category/**").hasAnyRole("MANAGER","ADMIN");
		http.authorizeRequests().antMatchers(HttpMethod.DELETE, "/api/category/**").hasAnyRole("MANAGER","ADMIN");
		
		http.authorizeRequests().anyRequest().permitAll();
		http.csrf().disable();
	
	}
	
	

	
}