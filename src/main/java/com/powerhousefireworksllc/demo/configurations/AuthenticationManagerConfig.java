package com.powerhousefireworksllc.demo.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.powerhousefireworksllc.demo.services.CustomUserDetailsService;

@Configuration
@EnableGlobalAuthentication
public class AuthenticationManagerConfig {
	
	private final CustomUserDetailsService customUserDetailsService; 
	private final PasswordEncoder passwordEncoder; 
	
	public AuthenticationManagerConfig(CustomUserDetailsService customUserDetailsService, PasswordEncoder passwordEncoder) {
		
		this.customUserDetailsService = customUserDetailsService; 
		this.passwordEncoder = passwordEncoder; 
		
	} 
	
	@Autowired
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder); 
		
	}
	
}