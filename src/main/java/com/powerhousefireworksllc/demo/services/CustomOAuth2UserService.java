package com.powerhousefireworksllc.demo.services;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;

import com.powerhousefireworksllc.demo.models.User;
import com.powerhousefireworksllc.demo.repositories.UserRepository;

public class CustomOAuth2UserService extends DefaultOAuth2UserService {
	
	@Autowired
	private UserRepository userRepository; 
	
	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		
		OAuth2User oAuth2User = super.loadUser(userRequest); 
		String email = oAuth2User.getAttribute("email"); 
		User user = userRepository.findByEmail(email); 
		String givenName = oAuth2User.getAttribute("given_name"); 
		String familyName = oAuth2User.getAttribute("family_name"); 
		
		if(user == null) {
			
			user = new User(); 
			user.setEmail(email); 
			user.setFname(givenName); 
			user.setLname(familyName); 
			
			this.userRepository.save(user); 
			
		} 
		
		return new DefaultOAuth2User(
				Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")),
				oAuth2User.getAttributes(),
				"name"); 
		
	} 
	
} 