package com.powerhousefireworksllc.demo.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService; 
import com.powerhousefireworksllc.demo.services.CustomUserDetailsService;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import java.io.IOException; 

@Configuration
@EnableWebSecurity
public class SecurityConfig { 
	
	@Autowired
	private CustomUserDetailsService customUserDetailsService; 
	
	@Bean
	public SecurityFilterChain customSecurityFilterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(requests -> requests
                .requestMatchers("/index", "/verify", "/password-reset").permitAll()
                .requestMatchers("/privacy-policy", "/terms-of-service").permitAll()
                .requestMatchers("/css/**", "/js/**", "/webjars/**").permitAll()
                .requestMatchers("/images/**", "/video/**").permitAll()
                .requestMatchers("/signup", "/perform_login", "/perform_logout").permitAll()
                .requestMatchers("/forgot-username", "/forgot-password").permitAll()
                .requestMatchers("/verifyToken", "/reset-password").permitAll()
                .anyRequest().authenticated())
                .formLogin(login -> login
                		.loginPage("/index")
                        .loginProcessingUrl("/perform_login")
                        .successHandler((request, response, auth) -> {
                        	System.out.print(response); 
                        	response.setContentType("application/json"); 
                        	response.setCharacterEncoding("UTF-8"); 
                        	String username = auth.getName();
                        	response.getWriter().write("{\"success\": true, \"username\": \"" + username + "\"}"); 
                        	response.getWriter().flush(); 
                        })
                        .failureHandler((request, response, exception) -> {
                        	System.out.print(response); 
                        	response.setContentType("application/json"); 
                        	response.setCharacterEncoding("UTF-8");
                        	response.getWriter().write("{\"success\": false }");
                        	response.getWriter().flush();
                        	// Log unsalted/unhashed submitted password
                        	String submittedPassword = request.getParameter("password");
                        	System.out.println("Submitted Password (unsalted/unhashed): " + submittedPassword);
                        	// Log stored salted/hashed password
                        	String username = request.getParameter("username");
                        	UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);
                        	System.out.println("Stored Hashed Password: " + userDetails.getPassword());
                        	// Log submitted password after salting/hashing
                        	PasswordEncoder passwordEncoder = passwordEncoder();
                        	String hashedSubmittedPassword = passwordEncoder.encode(submittedPassword);
                        	System.out.println("The password to be authenticated may or may not be getting salted and hashed to: " + hashedSubmittedPassword);
                        })
                        .permitAll())
                .logout(logout -> logout
                        .logoutUrl("/perform_logout")
                        .logoutSuccessUrl("/index")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID"))
                .csrf(csrf -> csrf.ignoringRequestMatchers("/signup", "/verifyToken", "/perform_logout", "/password-reset", "/forgot-password")); 
		
		return http.build(); 
		
	} 
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		
		return new BCryptPasswordEncoder(); 
		
	} 
	
} 