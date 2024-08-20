package com.powerhousefireworksllc.demo.configurations; 

import org.springframework.context.annotation.Bean; 
import org.springframework.context.annotation.Configuration; 
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder; 
import org.springframework.security.crypto.password.PasswordEncoder; 
import org.springframework.security.config.annotation.web.builders.HttpSecurity; 
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity; 
import org.springframework.security.web.SecurityFilterChain; 

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		
		return new BCryptPasswordEncoder(); 
		
	} 
	
/*	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {*/

        /*http.authorizeRequests(requests -> requests
                .antMatchers("/index", "/signup", "/resources/**")
                .permitAll()
                .anyRequests()
                .authenticated()
                .and()
                .logout()
                .permitAll()); 
		
		return http.build(); */
		
//		http.authorizeRequests(requests ->
//				.antMatchers("/css/**", "/js/**", "/images/**", "/video/**").permitAll()
//				.antMatchers("/", "/index", "/signup").permitAll()
//				.anyRequest()
//				.authenticated())
//				.formLogin(login -> login
//						.loginPage("/index")
//						.loginProcessingUrl("/index/login")
//						.successHandler(new Custom))
				
		
	}
	
}