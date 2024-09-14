package com.powerhousefireworksllc.demo.services; 

import com.powerhousefireworksllc.demo.models.User; 
import com.powerhousefireworksllc.demo.DTO.RegistrationDTO; 
import com.powerhousefireworksllc.demo.repositories.UserRepository; 
import com.powerhousefireworksllc.demo.exceptions.InvalidPasswordException;
import com.powerhousefireworksllc.demo.exceptions.InvalidUsernameFormatException;
import com.powerhousefireworksllc.demo.exceptions.InvalidEmailFormatException;
import com.powerhousefireworksllc.demo.exceptions.InvalidNameFormatException;
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
		
		if(!nameFormatValidator(registrationDTO.getFname()) || !nameFormatValidator(registrationDTO.getLname())) {
			
			String message = "First and last name fields must not remain blank."; 
			
			throw new InvalidNameFormatException(message); 
			
		}
		
		// Username format validation
		if(!usernameValidator(registrationDTO.getSignUpUsername())) { 
			
			String message = "Username field cannot be blank."; 
			
			throw new InvalidUsernameFormatException(message); 
			
		}
		
		/*
		 * Cross reference user submitted username with 
		 * possible existing username 
		 */ 
		
		if(this.userRepository.existsByUsername(registrationDTO.getSignUpUsername())) {
			
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
		user.setUsername(registrationDTO.getSignUpUsername()); 
		user.setPassword(registrationDTO.getPassword()); 
		user.setToken(token); 
		user.setVerified(false); 
		
		this.userRepository.save(user); 
		
		return user; 
		
	} 
	
	public Boolean verifyUser(String token) {
		
		Boolean verified = false; 
		User user = this.userRepository.findByToken(token); 
		
		if(user != null) {
			
			user.setVerified(true); 
			user.setToken(null); 
			this.userRepository.save(user); 
			
			verified = true; 
			
		}
		
		return verified; 
		
	} 
	
	public Boolean passwordValidator(String password, String confirm_password) {
		
		Boolean requirementsMet = true; 
		
		Boolean[] requirements = new Boolean[] {
			password.matches("^(?=.*[a-z]).*$"), 
			password.matches("^(?=.*[A-Z]).*$"), 
			password.length() >= 8, 
			password.matches(".*[0-9].*"), 
			password.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\,.<>/?].*"), 
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
		
		String regex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.(com|net|org|edu|gov|mil|biz|info|mobi|name|aero|jobs|museum)$"; 
		Pattern pattern = Pattern.compile(regex); 
		Matcher matcher = pattern.matcher(email); 
		
		return matcher.matches(); 
		
	} 
	
	public Boolean usernameValidator(String username) {
		
		return !username.equals(""); 
		
	} 
	
	public Boolean nameFormatValidator(String name) {
		
		return !name.equals(""); 
		
	} 
	
}