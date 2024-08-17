package com.powerhousefireworksllc.demo.models;

import jakarta.persistence.Entity; 
import jakarta.persistence.Table; 
import jakarta.persistence.Id; 
import jakarta.persistence.GenerationType; 
import jakarta.persistence.GeneratedValue; 
import jakarta.persistence.Column; 
import jakarta.validation.constraints.Email; 

@Entity
@Table(name = "users")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; 
	
	@Column(name = "fname")
	private String fname; 
	
	@Column(name = "lname")
	private String lname; 
	
	@Email
	@Column(name = "email")
	private String email; 
	
	@Column(name = "username")
	private String username; 
	
	@Column(name = "password")
	private String password; 
	
	@Column(name = "token")
	private String token; 
	
	@Column(name = "verified")
	private Boolean verified; 
	
	// other fields, getters, setters, and etc. will follow in further development 
	
	public Long getId() {
		
		return this.id; 
		
	} 
	
	public void setId(Long id) {
		
		this.id = id; 
		
	} 
	
	public String getFname() {
		
		return this.fname; 
		
	} 
	
	public void setFname(String fname) {
		
		this.fname = fname; 
		
	} 
	
	public String getLname() {
		
		return this.lname; 
		
	} 
	
	public void setLname(String lname) {
		
		this.lname = lname; 
		
	} 
	
	public String getFullName() {
		
		return this.fname + " " + this.lname; 
		
	}
	
	public String getEmail() {
		
		return this.email; 
		
	} 
	
	public void setEmail(String email) {
		
		this.email = email; 
		
	} 
	
	public String getUsername() {
		
		return this.username; 
		
	}
	
	public void setUsername(String username) {
		
		this.username = username; 
		
	} 
	
	public String getPassword() {
		
		return this.password; 
		
	}
	
	public void setPassword(String password) {
		
		this.password = password; 
		
	}
	
	public String getToken() {
		
		return this.token; 
		
	} 
	
	public void setToken(String token) {
		
		this.token = token; 
		
	} 
	
	public Boolean getVerified() {
		
		return this.verified; 
		
	} 
	
	public void setVerified(Boolean verified) {
		
		this.verified = verified; 
		
	} 
	
} 