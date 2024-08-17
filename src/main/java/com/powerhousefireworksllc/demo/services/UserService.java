package com.powerhousefireworksllc.demo.services; 

import com.powerhousefireworksllc.demo.models.User; 
import com.powerhousefireworksllc.demo.DTO.RegistrationDTO; 
import com.powerhousefireworksllc.demo.repositories.UserRepository; 

import org.springframework.stereotype.Service; 
import org.springframework.beans.factory.annotation.Autowired; 

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository; 
	
	public User registerUser(RegistrationDTO registrationDTO, String token) {
		
		// Perform validation 
		/* 
		 * . . . Don't forget to hash password 
		 */
		
		// Save user with unverified status 
		User user = new User(); 
		user.setFname(registrationDTO.getFname()); 
		user.setLname(registrationDTO.getLname()); 
		user.setEmail(registrationDTO.getEmail()); 
		user.setUsername(registrationDTO.getUsername()); 
		user.setPassword(registrationDTO.getPassword()); 
		user.setToken(registrationDTO.getToken()); 
		user.setVerified(false); 
		
		userRepository.save(user); 
		
		return user; 
		
	} 
	
	public Boolean verifyUser(String token) {
		
		User user = userRepository.findByToken(token); 
		
		if(user != null) {
			
			user.setVerified(true); 
			user.setToken(null); 
			userRepository.save(user); 
			
		}
		
		return false; 
		
	}
	
}