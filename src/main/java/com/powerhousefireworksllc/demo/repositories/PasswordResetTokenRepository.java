package com.powerhousefireworksllc.demo.repositories;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.powerhousefireworksllc.demo.models.PasswordResetToken;

@Repository
public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {
	
	PasswordResetToken findByToken(String token); 
	PasswordResetToken findByUsername(String username); 
	void deleteByToken(String token); 
	List<PasswordResetToken> findAllByExpiryDateBefore(LocalDateTime now); 
	
}