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
import java.util.UUID;

import com.powerhousefireworksllc.demo.DTO.RegistrationDTO;
import com.powerhousefireworksllc.demo.services.EmailService; 

@Controller 
public class IndexController { 
	
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
	public ResponseEntity<RegistrationDTO> register(@RequestBody RegistrationDTO registrationDTO) {
		
		String token = UUID.randomUUID().toString(); 
		System.out.println(token); 
		
		this.emailService.sendVerificationEmail(registrationDTO.getEmail(), token); 
		
		return new ResponseEntity<>(registrationDTO, HttpStatus.OK); 
		
	}
	
} 