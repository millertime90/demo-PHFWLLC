package com.powerhousefireworksllc.demo.services; 

import com.powerhousefireworksllc.demo.models.User; 
import com.powerhousefireworksllc.demo.DTO.RegistrationDTO; 
import com.powerhousefireworksllc.demo.repositories.UserRepository; 
import com.powerhousefireworksllc.demo.exceptions.InvalidPasswordException; 
import com.powerhousefireworksllc.demo.exceptions.InvalidEmailFormatException; 
import com.powerhousefireworksllc.demo.exceptions.EmailAlreadyExistsException; 
import com.powerhousefireworksllc.demo.exceptions.UsernameAlreadyExistsException; 

import org.springframework.stereotype.Service; 
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.security.crypto.password.PasswordEncoder; 

import java.util.regex.Pattern; 
import java.util.regex.Matcher; 

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository; 
	
	@Autowired
	private PasswordEncoder passwordEncoder; 
	
	public User registerUser(RegistrationDTO registrationDTO, String token) throws Exception { 
		
		// 	Perform validation 
		
		// Email format validation 
		if(!emailValidator(registrationDTO.getEmail())) {
			
			String message = "Invalid email (format)"; 
			
			throw new InvalidEmailFormatException(message); 
			
		} 
		
		/* 
		 * Cross reference user submitted email address with 
		 * possible existing email address 
		 */ 
		
		if(this.userRepository.existsByEmail(registrationDTO.getEmail())) {
			
			String message = "Email already exists"; 
			
			throw new EmailAlreadyExistsException(message); 
			
		} 
		
		/*
		 * Cross reference user submitted username with 
		 * possible existing username 
		 */ 
		
		if(this.userRepository.existsByUsername(registrationDTO.getUsername())) {
			
			String message = "Username already exists"; 
			
			throw new UsernameAlreadyExistsException(message); 
			
		} 
		
		// Password Validation 
		if(!passwordValidator(registrationDTO.getPassword(), registrationDTO.getConfirm_password())) { 
					
			String message = "Invalid password due to unmet requirements."; 
					
			throw new InvalidPasswordException(message); 
					
		} 
		else { // Password salt and hash 
					
			String hashedPassword = this.passwordEncoder.encode(registrationDTO.getPassword()); 
			registrationDTO.setPassword(hashedPassword); 
					
		} 
		
		// Save user with unverified status 
		User user = new User(); 
		user.setFname(registrationDTO.getFname()); 
		user.setLname(registrationDTO.getLname()); 
		user.setEmail(registrationDTO.getEmail()); 
		user.setUsername(registrationDTO.getUsername()); 
		user.setPassword(registrationDTO.getPassword()); 
		user.setToken(registrationDTO.getToken()); 
		user.setVerified(false); 
		
		this.userRepository.save(user); 
		
		return user; 
		
	} 
	
	public Boolean verifyUser(String token) {
		
		User user = this.userRepository.findByToken(token); 
		
		if(user != null) {
			
			user.setVerified(true); 
			user.setToken(null); 
			this.userRepository.save(user); 
			
		}
		
		return false; 
		
	} 
	
	public Boolean passwordValidator(String password, String confirm_password) {
		
		Boolean requirementsMet = true; 
		
		Boolean[] requirements = new Boolean[] {
			password.matches("^(?=.*[a-z]).*$"), 
			password.matches("^(?=.*[A-Z]).*$"), 
			password.length() >= 8, 
			password.matches(".*[0-9].*"), 
			password.matches(".*[!@#$%^&*()_+\\-=[\\]{};':\"\\\\,.<>/?]"), 
			!password.matches(".*\\s.*"), 
			password.equals(confirm_password)
		}; 
		
		for(Boolean requirement: requirements) {
			
			if(!requirement) { 
				
				requirementsMet = false; 
				break; 
				
			}
			
		} 
		
		return requirementsMet; 
		
	} 
	
	public Boolean emailValidator(String email) {
		
		String regex = "\\.(com|net|org|edu|gov|mil|biz|info|mobi|name|aero|jobs|museum)$"; 
		Pattern pattern = Pattern.compile(regex); 
		Matcher matcher = pattern.matcher(email); 
		
		return matcher.matches(); 
		
	}
	
}