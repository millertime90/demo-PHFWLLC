package com.powerhousefireworksllc.demo.services; 

import org.springframework.stereotype.Service; 
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.beans.factory.annotation.Value; 

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.SimpleMailMessage; 

@Service
public class EmailService {
	
	@Autowired 
	private JavaMailSender mailSender; 
	
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
		
		this.mailSender.send(message); 
		
	}
	
	public void sendPasswordResetLink(String name, String username, String token, String email) {
		
		SimpleMailMessage message = new SimpleMailMessage(); 
		String passwordResetLink = baseURL + "/password-reset?username=" + username + "&token=" + token; 
		String messageBody = 
				"Hello " + name + "\n\n" +
				"You are receiving this email message to inform that a request\n" + 
				"was received to reset your password for your WC account.\n" + 
				"If you believe you are receiving this email message in error,\n" + 
				"then please disregard this email message.\n\n" + 
				"Best regards,\n-WC\n\nClick on the following link to redirect to\n" + 
				"the password reset page.\n\n" + passwordResetLink; 
		
		message.setTo(email); 
		message.setSubject("WC - Password Reset Request Received"); 
		message.setText(messageBody); 
		
		this.mailSender.send(message); 
		
	}
	
}