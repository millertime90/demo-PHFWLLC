package com.powerhousefireworksllc.demo.services; 

import org.springframework.stereotype.Service; 
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.beans.factory.annotation.Value; 

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.SimpleMailMessage; 

@Service
public class EmailService {
	
	@Autowired 
	JavaMailSender mailSender; 
	
	@Value("${app.base.url}")
	private String baseURL; 
	
	public void sendVerificationEmail(String name, String email, String token) {
		
		SimpleMailMessage message = new SimpleMailMessage(); 
		String verificationLink = baseURL + "/verify?token=" + token; 
		String messageBody = 
				"Hello " + name + "\n\n" +
				"Thanks for signing up for a free account at WC.\n" + 
				"Remember, by signing up for a new account, you can save 15% off your first order that is\n" + 
				"at or above $100.\n\nBest regards,\n-WC\n\nPlease click the following link to verify your email " + verificationLink; 
		
		message.setTo(email); 
		message.setSubject("Email Verification"); 
		message.setText(messageBody); 
		
		mailSender.send(message); 
		
	}
	
}