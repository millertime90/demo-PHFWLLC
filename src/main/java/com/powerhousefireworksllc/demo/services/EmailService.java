package com.powerhousefireworksllc.demo.services; 

import org.springframework.stereotype.Service; 
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.SimpleMailMessage; 

@Service
public class EmailService {
	
	@Autowired JavaMailSender mailSender; 
	
	public void sendVerificationEmail(String email, String token) {
		
		SimpleMailMessage message = new SimpleMailMessage(); 
		String verificationLink = "https://rocky-plateau-85156-606894c349ef.herokuapp.com/verify?token=" + token; 
		String messageBody = "Please click the following link to verify your email " + verificationLink; 
		
		message.setTo(email); 
		message.setSubject("Email Verification"); 
		message.setText(messageBody); 
		
		mailSender.send(message); 
		
	}
	
}