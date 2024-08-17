package com.powerhousefireworksllc.demo.controllers; 

import org.springframework.stereotype.Controller; 
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestBody; 
import org.springframework.web.bind.annotation.GetMapping; 
import org.springframework.web.bind.annotation.PostMapping; 
import org.springframework.beans.factory.annotation.Autowired; 

import org.springframework.web.servlet.ModelAndView; 
import org.springframework.http.ResponseEntity; 
import org.springframework.http.HttpStatus; 

import java.util.Map; 
import java.util.HashMap; 
import java.util.UUID;

import com.powerhousefireworksllc.demo.DTO.RegistrationDTO; 
import com.powerhousefireworksllc.demo.services.UserService; 
import com.powerhousefireworksllc.demo.services.EmailService; 
// import com.powerhousefireworksllc.demo.models.User; 

@Controller 
public class IndexController { 
	
	@Autowired
	private UserService userService; 
	
	@Autowired
	private EmailService emailService; 
	
	@GetMapping({"/", "/index"})
	public ModelAndView getIndex() { 
		
		System.out.println("`getIndex` method invoked."); 
		
		ModelAndView modelAndView = new ModelAndView("index"); 
		return modelAndView; 
		
	} 
	
	@PostMapping({"/", "/signup"})
	@ResponseBody
	public ResponseEntity<Map<String, String>> register(@RequestBody RegistrationDTO registrationDTO) {
		
		Map<String, String> response = new HashMap<String, String>(); 
		String token = UUID.randomUUID().toString(); 
		
		// Validate and save user data 
		/*User user =*/ // userService.registerUser(registrationDTO, token); 
		
		response.put("message",  "Form successfully submitted"); 
		response.put("email", registrationDTO.getEmail()); 
		
		System.out.println(token); 
		
		// Send verification email 
		this.emailService.sendVerificationEmail(registrationDTO.getFullName(), registrationDTO.getEmail(), token); 
		
		return new ResponseEntity<>(response, HttpStatus.OK); 
		
	}
	
} 