package com.powerhousefireworksllc.demo.controllers; 

import com.powerhousefireworksllc.demo.services.UserService; 

import org.springframework.stereotype.Controller; 
import org.springframework.web.bind.annotation.GetMapping; 
import org.springframework.web.bind.annotation.RequestParam; 
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.ModelAndView; 
import org.springframework.http.ResponseEntity; 
import org.springframework.http.HttpStatus; 

@Controller
public class EmailVerificationController {
	
	@Autowired
	private UserService userService; 
	
	@Value("${app.base.url")
	private String baseURL; 
	
	@GetMapping({"/", "/verify"})
	public ModelAndView getVerifyView(@RequestParam("token") String token) {
		
		ModelAndView modelAndView = new ModelAndView("verify"); 
		modelAndView.addObject("token", token); 
		return modelAndView; 
		
	}
	
	@GetMapping({"/", "/verifyToken"})
	@ResponseBody
	public ResponseEntity<Map<String, String>> verifyEmail(@RequestParam("token") String token) {
		
		System.out.println("`verifyEmail` invoked"); 
		
		Map<String, String> response = new HashMap<>(); 
		Boolean isVerified = this.userService.verifyUser(token); 
		System.out.println("User exists by token (" + token + "): " + isVerified); 
		
		String verifySuccess = "Email verified successfully. Your account is now activated."; 
		String verifyUnsuccess = "Invalid or expired token. Recreate account."; 
		
		if(isVerified) {
			
			response.put("message", verifySuccess); 
			response.put("bodyText", "login, login"); 
			response.put("redirectURL", baseURL + "/index?verified=true"); 
			return new ResponseEntity<>(response, HttpStatus.OK); 
			
		} 
		else {
			
			response.put("message", verifyUnsuccess); 
			response.put("bodyText", "recreate account, registration"); 
			response.put("redirectURL", baseURL + "/index?verified=false"); 
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST); 
			
		}
		
	}
	
}