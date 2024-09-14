package com.powerhousefireworksllc.demo.DTO; 

public class RegistrationDTO {
	
	// FIELDS 
	
	private String fname; 
	private String lname; 
	private String email; 
	private String signUpUsername; 
	private String password; 
	private String confirm_password; 
	private String token; 
	
	// GETTERS AND SETTERS 
	
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
	
	public String getSignUpUsername() {
		
		return this.signUpUsername; 
		
	} 
	
	public void setSignUpUserName(String username) {
		
		this.signUpUsername = username; 
		
	} 
	
	public String getPassword() {
		
		return this.password; 
		
	} 
	
	public void setPassword(String password) {
		
		this.password = password; 
		
	} 
	
	public String getConfirm_password() {
		
		return this.confirm_password; 
		
	} 
	
	public void setConfirm_password(String confirm_password) {
		
		this.confirm_password = confirm_password; 
		
	} 
	
	public String getToken() {
		
		return this.token; 
		
	} 
	
	public void setToken(String token) {
		
		this.token = token; 
		
	}
	
}