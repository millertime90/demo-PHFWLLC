package com.powerhousefireworksllc.demo.models;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "password_reset_tokens")
public class PasswordResetToken {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; 
	
	@Column(name = "username")
	private String username; 
	
	@Column(name = "token")
	private String token; 
	
	@Column(name = "expiryDate")
	private LocalDateTime expiryDate; 
	
	public Long getId() {
		
		return this.id; 
		
	} 
	
	public void setId(Long id) {
		
		this.id = id; 
		
	} 
	
	public String getUsername() {
		
		return this.username; 
		
	} 
	
	public void setUsername(String username) {
		
		this.username = username; 
		
	} 
	
	public String getToken() {
		
		return this.token; 
		
	} 
	
	public void setToken(String token) {
		
		this.token = token; 
		
	} 
	
	public LocalDateTime getExpiryDate() {
		
		return this.expiryDate; 
		
	} 
	
	public void setExpiryDate(LocalDateTime expiryDate) {
		
		this.expiryDate = expiryDate; 
		
	} 
	
}