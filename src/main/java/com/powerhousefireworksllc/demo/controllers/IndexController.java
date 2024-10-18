package com.powerhousefireworksllc.demo.controllers; 

import org.springframework.stereotype.Controller; 
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
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
import com.powerhousefireworksllc.demo.services.PasswordResetTokenService;
import com.powerhousefireworksllc.demo.models.User; 
import com.powerhousefireworksllc.demo.exceptions.EmailAlreadyExistsException;
import com.powerhousefireworksllc.demo.exceptions.EmailDoesNotExistException;
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
	
	@Autowired
	private PasswordResetTokenService passwordResetTokenService; 
	
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
		this.userService.registerUser(registrationDTO, token); 
		
		response.put("message",  "Form successfully submitted"); 
		response.put("email", registrationDTO.getEmail()); 
		
		// Send verification email 
		this.emailService.sendVerificationEmail(registrationDTO.getFullName(), registrationDTO.getEmail(), token); 
		
		return new ResponseEntity<>(response, HttpStatus.OK); 
		
	} 
	
	@GetMapping(value = {"/", "/forgot-username"})
	@ResponseBody
	public ResponseEntity<Map<String, String>> retrieveUsername(@RequestParam("emailAddress") String emailAddress) throws Exception {
		
		Map<String, String> response = new HashMap<>(); 
		User user = this.userService.verifyUserByEmail(emailAddress); 
		String username = user.getUsername(); 
		
		response.put("username", username); 
		
		return new ResponseEntity<>(response, HttpStatus.OK); 
		
	}
	
	@PostMapping({"/", "/forgot-password"})
	@ResponseBody
	public ResponseEntity<Map<String, String>> emailPasswordResetLink(@RequestBody Map<String, String> singleEntryMap) throws Exception {
		
		Map<String, String> response = new HashMap<>(); 
		String emailAddress = singleEntryMap.get("emailAddress"); 
		User user = this.userService.verifyUserByEmail(emailAddress); 
		String userEmailAddress = user.getEmail(); 
		String pwdResetTokenValue = UUID.randomUUID().toString(); 
		String message = "Password reset link sent to " + userEmailAddress; 
		
		this.passwordResetTokenService.storePasswordResetToken(user, pwdResetTokenValue); 
		this.emailService.sendPasswordResetLink(user.getFullName(), user.getUsername(), pwdResetTokenValue, userEmailAddress); 
		response.put("message", message); 
		
		return new ResponseEntity<>(response, HttpStatus.OK); 
		
	}
	
	@ExceptionHandler(EmailAlreadyExistsException.class)
	public ResponseEntity<Map<String, String>> handleEmailAlreadyExistsException(EmailAlreadyExistsException ex) {
		
		Map<String, String> response = new HashMap<>(); 
		response.put("message", ex.getMessage()); 
		
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST); 
		
	} 
	
	@ExceptionHandler(EmailDoesNotExistException.class)
	public ResponseEntity<Map<String, String>> handleEmailDoesNotExistException(EmailDoesNotExistException ex) {
		
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