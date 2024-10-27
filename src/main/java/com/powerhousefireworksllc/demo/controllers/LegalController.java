package com.powerhousefireworksllc.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LegalController {
	
	@GetMapping({"/", "/privacy-policy"})
	public ModelAndView getPrivacyPolicy() {
		
		ModelAndView modelAndView = new ModelAndView("privacy"); 
		return modelAndView; 
		
	} 
	
	@GetMapping({"/", "/terms-of-service"})
	public ModelAndView getTermsOfService() {
		
		ModelAndView modelAndView = new ModelAndView("termsofservice"); 
		return modelAndView; 
		
	}
	
}