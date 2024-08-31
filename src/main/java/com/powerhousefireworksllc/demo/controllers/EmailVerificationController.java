package com.powerhousefireworksllc.demo.controllers; 

import com.powerhousefireworksllc.demo.services.UserService; 

import org.springframework.stereotype.Controller; 
import org.springframework.web.bind.annotation.GetMapping; 
import org.springframework.web.bind.annotation.RequestParam; 
import org.springframework.web.bind.annotation.ResponseBody; 
import org.springframework.beans.factory.annotation.Autowired; 

import org.springframework.web.servlet.ModelAndView; 
import org.springframework.http.ResponseEntity; 
import org.springframework.http.HttpStatus; 

@Controller
public class EmailVerificationController {
	
	@Autowired
	private UserService userService; 
	
	@GetMapping({"/", "/verify"})
	public ModelAndView getVerifyView(@RequestParam("token") String token) {
		
		ModelAndView modelAndView = new ModelAndView("verify"); 
		modelAndView.addObject("token", token); 
		return modelAndView; 
		
	}
	
	@GetMapping({"/", "/verifyToken"})
	@ResponseBody
	public ResponseEntity<String> verifyEmail(@RequestParam("token") String token) {
		
		System.out.println("`verifyEmail` invoked"); 
		
		Boolean isVerified = this.userService.verifyUser(token); 
		System.out.println("User exists by token (" + token + "): " + isVerified); 
		
		String verifySuccess = "Email verified successfully. Your account is now activated."; 
		String verifyUnsuccess = "Invalid or expired token."; 
		
		if(isVerified) {
			
			return new ResponseEntity<>(verifySuccess, HttpStatus.OK); 
			
		} 
		else {
			
			return new ResponseEntity<>(verifyUnsuccess, HttpStatus.BAD_REQUEST); 
			
		}
		
	}
	
}