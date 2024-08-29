package com.powerhousefireworksllc.demo.configurations;

import org.springframework.context.annotation.Bean;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain; 

import static org.springframework.security.config.Customizer.withDefaults; 

@Configuration
@EnableWebSecurity
public class SecurityConfig { 
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		
		return new BCryptPasswordEncoder(); 
		
	} 
	
	@Bean
	public SecurityFilterChain customSecurityFilterChain(HttpSecurity http) throws Exception {
		
//		http.authorizeHttpRequests(requests -> requests.anyRequest().authenticated()); 
//		http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)); 
//		http.httpBasic(withDefaults()); 
		http.authorizeHttpRequests()
			.requestMatchers("/index").permitAll()
			.requestMatchers("/css/**").permitAll()
			.requestMatchers("/js/**").permitAll()
			.requestMatchers("/images/**").permitAll()
			.requestMatchers("/webjars/**").permitAll()
			.requestMatchers("/video/**").permitAll()
			.requestMatchers("/signup").permitAll()
			.anyRequest().authenticated(); 
		
		return http.build(); 
		
	} 
	
//	@Bean
//	public UserDetailsService userDetailsService() {
//		
//		UserDetails user1 = User.withUsername("user1")
//								.password("{noop}password1")
//								.roles("USER")
//								.build(); 
//		
//		UserDetails admin1 = User.withUsername("admin1")
//								 .password("{noop}password2")
//								 .roles("ADMIN")
//								 .build(); 
//		
//		return new InMemoryUserDetailsManager(user1, admin1); 
//		
//	}
	
} 