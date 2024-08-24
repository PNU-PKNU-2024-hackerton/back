package com.example.SecurityJWT.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
}
