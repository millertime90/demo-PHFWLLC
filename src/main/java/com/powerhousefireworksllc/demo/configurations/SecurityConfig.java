package com.powerhousefireworksllc.demo.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig { 
	
	@Bean
	public SecurityFilterChain customSecurityFilterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(requests -> requests
                .requestMatchers("/index", "/verify").permitAll()
                .requestMatchers("/css/**", "/js/**", "/webjars/**").permitAll()
                .requestMatchers("/images/**", "/video/**").permitAll()
                .requestMatchers("/signup", "/verifyToken", "/perform_login").permitAll()
                .anyRequest().authenticated())
                .formLogin(login -> login
                		.loginPage("/index")
                        .loginProcessingUrl("/perform_login")
                        .successHandler((request, response, exception) -> {
                        	response.sendRedirect("/index?loginSuccess=true");
                        })
                        .failureHandler((request, response, exception) -> {
                        	response.sendRedirect("/index?loginError=true");
                        })
                        .permitAll())
                .logout(logout -> logout
                        .logoutUrl("/perform_logout")
                        .logoutSuccessUrl("/index")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID"))
                .csrf(csrf -> csrf.ignoringRequestMatchers("/signup", "/verifyToken")); 
		
		return http.build(); 
		
	} 
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		
		return new BCryptPasswordEncoder(); 
		
	} 
	
} 