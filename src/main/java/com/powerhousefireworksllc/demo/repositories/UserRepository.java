package com.powerhousefireworksllc.demo.repositories; 

import com.powerhousefireworksllc.demo.models.User; 

import org.springframework.data.jpa.repository.JpaRepository; 
import org.springframework.stereotype.Repository; 

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	Boolean existsByUsername(String username); 
	Boolean existsByEmail(String email); 
	User findByUsername(String username); 
	User findByEmail(String Email); 
	User findByToken(String token); 
	
} 