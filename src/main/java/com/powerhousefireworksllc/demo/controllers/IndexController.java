package com.powerhousefireworksllc.demo.controllers; 

import org.springframework.stereotype.Controller; 
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping; 
import org.springframework.web.bind.annotation.PostMapping; 
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.web.bind.annotation.ExceptionHandler; 

import org.springframework.web.servlet.ModelAndView; 
import org.springframework.http.ResponseEntity; 
import org.springframework.http.HttpStatus; 

import java.util.Map; 
import java.util.HashMap; 
import java.util.UUID;

import com.powerhousefireworksllc.demo.DTO.RegistrationDTO; 
import com.powerhousefireworksllc.demo.services.UserService; 
import com.powerhousefireworksllc.demo.services.EmailService; 
import com.powerhousefireworksllc.demo.models.User; 
import com.powerhousefireworksllc.demo.exceptions.EmailAlreadyExistsException; 
import com.powerhousefireworksllc.demo.exceptions.InvalidEmailFormatException;
import com.powerhousefireworksllc.demo.exceptions.InvalidNameFormatException;
import com.powerhousefireworksllc.demo.exceptions.InvalidPasswordException;
import com.powerhousefireworksllc.demo.exceptions.InvalidUsernameFormatException;
import com.powerhousefireworksllc.demo.exceptions.UsernameAlreadyExistsException; 


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
	public ResponseEntity<Map<String, String>> register(@RequestBody RegistrationDTO registrationDTO) throws Exception {
		
		Map<String, String> response = new HashMap<>(); 
		String token = UUID.randomUUID().toString(); 
		System.out.println("New user token generated:" + token); 
		
		// Validate and save user data 
		User user = this.userService.registerUser(registrationDTO, token); 
		
		response.put("message",  "Form successfully submitted"); 
		response.put("email", registrationDTO.getEmail()); 
		
		// Send verification email 
		this.emailService.sendVerificationEmail(registrationDTO.getFullName(), registrationDTO.getEmail(), token); 
		
		return new ResponseEntity<>(response, HttpStatus.OK); 
		
	} 
	
	@ExceptionHandler(EmailAlreadyExistsException.class)
	public ResponseEntity<Map<String, String>> handleEmailAlreadyExistsException(EmailAlreadyExistsException ex) {
		
		Map<String, String> response = new HashMap<>(); 
		response.put("message", ex.getMessage()); 
		
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST); 
		
	} 
	
	@ExceptionHandler(InvalidEmailFormatException.class)
	public ResponseEntity<Map<String, String>> handleInvalidEmailFormatException(InvalidEmailFormatException ex) {
		
		Map<String, String> response = new HashMap<>(); 
		response.put("message", ex.getMessage()); 
		
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST); 
		
	} 
	
	@ExceptionHandler(InvalidNameFormatException.class)
	public ResponseEntity<Map<String, String>> handleInvalidNameFormatException(InvalidNameFormatException ex) {
		
		Map<String, String> response = new HashMap<>(); 
		response.put("message", ex.getMessage()); 
		
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST); 
		
	}
	
	@ExceptionHandler(InvalidUsernameFormatException.class)
	public ResponseEntity<Map<String, String>> handleInvalidUsernameFormatException(InvalidUsernameFormatException ex) {
		
		Map<String, String> response = new HashMap<>(); 
		response.put("message", ex.getMessage()); 
		
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST); 
		
	}
	
	@ExceptionHandler(UsernameAlreadyExistsException.class)
	public ResponseEntity<Map<String, String>> handleUsernameAlreadyExistsException(UsernameAlreadyExistsException ex) {
		
		Map<String, String> response = new HashMap<>(); 
		response.put("message", ex.getMessage()); 
		
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST); 
		
	}
	
	@ExceptionHandler(InvalidPasswordException.class)
	public ResponseEntity<Map<String, String>> handleInvalidPasswordException(InvalidPasswordException ex) {
		
		Map<String, String> response = new HashMap<>(); 
		response.put("message", ex.getMessage()); 
		
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST); 
		
	} 
	
} 