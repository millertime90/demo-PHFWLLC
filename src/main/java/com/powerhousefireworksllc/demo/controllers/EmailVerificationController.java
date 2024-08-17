package com.powerhousefireworksllc.demo.controllers; 

import com.powerhousefireworksllc.demo.services.UserService; 

import org.springframework.stereotype.Controller; 
import org.springframework.web.bind.annotation.GetMapping; 
import org.springframework.web.bind.annotation.RequestParam; 
import org.springframework.beans.factory.annotation.Autowired; 

import org.springframework.web.servlet.ModelAndView; 
import org.springframework.http.ResponseEntity; 
import org.springframework.http.HttpStatus; 

@Controller
public class EmailVerificationController {
	
	@Autowired
	private UserService userService; 
	
	@GetMapping({"/", "/verifyToken"})
	public ResponseEntity<String> verifyEmail(@RequestParam("token") String token) {
		
		Boolean isVerified = userService.verifyUser(token); 
		
		String verifySuccess = "Email verified successfully!"; 
		String verifyUnsuccess = "Invalid or expired token."; 
		
		if(isVerified) {
			
			return new ResponseEntity<>(verifySuccess, HttpStatus.OK); 
			
		} 
		else {
			
			return new ResponseEntity<>(verifyUnsuccess, HttpStatus.BAD_REQUEST); 
			
		}
		
	}
	
}