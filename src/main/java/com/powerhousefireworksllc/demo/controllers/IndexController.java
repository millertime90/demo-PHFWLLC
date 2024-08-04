package com.powerhousefireworksllc.demo.controllers; 

import org.springframework.stereotype.Controller; 
import org.springframework.web.bind.annotation.GetMapping; 
import org.springframework.web.bind.annotation.PostMapping; 
import org.springframework.web.servlet.ModelAndView; 
import org.springframework.http.ResponseEntity; 
import org.springframework.http.HttpStatus; 
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestBody; 
import com.powerhousefireworksllc.demo.DTO.RegistrationDTO; 

@Controller 
public class IndexController { 
	
	@GetMapping({"/", "/index"})
	public ModelAndView getIndex() { 
		
		System.out.println("`getIndex` method invoked."); 
		
		ModelAndView modelAndView = new ModelAndView("index"); 
		return modelAndView; 
		
	} 
	
	@PostMapping({"/", "/signup"})
	@ResponseBody
	public ResponseEntity<RegistrationDTO> register(@RequestBody RegistrationDTO registrationDTO) {
		
		return new ResponseEntity<>(registrationDTO, HttpStatus.OK); 
		
	}
	
} 