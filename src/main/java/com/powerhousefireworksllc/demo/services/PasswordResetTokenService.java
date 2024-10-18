package com.powerhousefireworksllc.demo.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.powerhousefireworksllc.demo.exceptions.InvalidPasswordResetTokenException;
import com.powerhousefireworksllc.demo.models.PasswordResetToken;
import com.powerhousefireworksllc.demo.models.User;
import com.powerhousefireworksllc.demo.repositories.PasswordResetTokenRepository;

@Service
public class PasswordResetTokenService { 
	
	@Autowired
	PasswordResetTokenRepository passwordResetTokenRepository; 
	
	public void storePasswordResetToken(User user, String token) {
		
		PasswordResetToken passwordResetToken = new PasswordResetToken(); 
		passwordResetToken.setUsername(user.getUsername()); 
		passwordResetToken.setToken(token); 
		passwordResetToken.setExpiryDate(LocalDateTime.now().plusHours(1)); 
		
		this.passwordResetTokenRepository.save(passwordResetToken); 
		
	} 
	
	@Scheduled(cron = "0 0 * * * ?")
	public void removeExpiredTokens() {
		
		List<PasswordResetToken> expiredTokens = this.passwordResetTokenRepository.findAllByExpiryDateBefore(LocalDateTime.now()); 
		this.passwordResetTokenRepository.deleteAll(expiredTokens); 
		
	} 
	
	public void validatePasswordResetToken(String username, String token) throws Exception {
		
		PasswordResetToken passwordResetToken = this.passwordResetTokenRepository.findByToken(token); 
		if(passwordResetToken == null || !passwordResetToken.getUsername().equals(username)) {
			
			String message = "User password reset token is invalid"; 
			throw new InvalidPasswordResetTokenException(message); 
			
		} 
		
		if(passwordResetToken.getExpiryDate().isBefore(LocalDateTime.now())) {
			
			String message = "User password reset token has expired"; 
			throw new InvalidPasswordResetTokenException(message); 
			
		} 
		
	}
	
}