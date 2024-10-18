package com.powerhousefireworksllc.demo.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.powerhousefireworksllc.demo.exceptions.InvalidPasswordException;
import com.powerhousefireworksllc.demo.exceptions.InvalidPasswordResetTokenException;
import com.powerhousefireworksllc.demo.models.User;
import com.powerhousefireworksllc.demo.services.PasswordResetTokenService;
import com.powerhousefireworksllc.demo.services.UserService;

@Controller
public class PasswordResetController {
	
	@Autowired
	private UserService userService; 
	
	@Autowired
	private PasswordResetTokenService passwordResetTokenService; 
	
	@GetMapping({"/", "/password-reset"})
	public ModelAndView renderPasswordResetView(@RequestParam("username") String username, @RequestParam("token") String token) {
		
		ModelAndView modelAndView = new ModelAndView("passwordreset"); 
		modelAndView.addObject("username", username); 
		modelAndView.addObject("token", token); 
		
		return modelAndView; 
		
	}
	
	@PatchMapping({"/", "/reset-password"})
	@ResponseBody
	public ResponseEntity<Map<String, String>> resetPassword(@RequestParam("username") String username, @RequestParam("token") String token, @RequestBody Map<String, String> passwordResetData) throws Exception {
		
		Map<String, String> response = new HashMap<>(); 
		
		try {
		
			this.passwordResetTokenService.validatePasswordResetToken(username, token); 
			
			User user = this.userService.getUserByUsername(username); 
			this.userService.updatePassword(user, passwordResetData.get("password"), passwordResetData.get("confirm_password")); 
		
			String message = "Your password has reset successfully."; 
			response.put("message", message); 
		
		}
		catch(InvalidPasswordResetTokenException ex) {
			
			response.put("message", ex.getMessage()); 
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST); 
			
		}
		
		return new ResponseEntity<>(response, HttpStatus.OK); 
		
	} 
	
	@ExceptionHandler(InvalidPasswordException.class)
	public ResponseEntity<Map<String, String>> handleInvalidPasswordException(InvalidPasswordException ex) {
		
		Map<String, String> response = new HashMap<>(); 
		response.put("message", ex.getMessage()); 
		
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST); 
		
	} 
	
}