package com.example.SecurityJWT.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.example.SecurityJWT.dto.UserResponseDto;
import com.example.SecurityJWT.entity.UserEntity;
import com.example.SecurityJWT.repository.UserRepository;

@Service

public class UserService {

	@Autowired
	private UserRepository userRepository;

	public Optional<UserEntity> getUserById(int id){
		return userRepository.findById(id);
	}

	public UserEntity getMyInfo() {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		return userRepository.findByUsername(userDetails.getUsername())
			.orElseThrow(() -> new RuntimeException("User Not Found"));
	}
}
