package com.powerhousefireworksllc.demo.controllers; 

import org.springframework.web.bind.annotation.RestController; 
import org.springframework.web.bind.annotation.RequestMapping; 
import org.springframework.web.bind.annotation.GetMapping; 
import org.springframework.web.bind.annotation.PostMapping; 
import org.springframework.web.servlet.ModelAndView; 

@RestController 
@RequestMapping("/index")
public class IndexController { 
	
	@GetMapping("/index")
	public ModelAndView getIndex() { 
		
		System.out.println("`getIndex` method invoked"); 
		
		ModelAndView modelAndView = new ModelAndView("index"); 
		return modelAndView; 
		
	} 
	
	
} 