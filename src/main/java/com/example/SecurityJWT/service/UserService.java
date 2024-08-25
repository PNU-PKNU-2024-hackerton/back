package com.example.SecurityJWT.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.SecurityJWT.entity.UserEntity;
import com.example.SecurityJWT.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	public Optional<UserEntity> getUserById(int id) {
		return userRepository.findById(id);
	}

	public UserEntity getMyInfo() {
		UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		return userRepository.findByUsername(userDetails.getUsername())
			.orElseThrow(() -> new RuntimeException("User Not Found"));
	}

	public void changePassword(String currentPassword, String newPassword) {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserEntity user = userRepository.findByUsername(userDetails.getUsername())
			.orElseThrow(() -> new RuntimeException("User not Found"));

		if (!passwordEncoder.matches(currentPassword, user.getPassword())){
			throw new RuntimeException("Wrong password");
		}
		user.setPassword(passwordEncoder.encode(newPassword));
		userRepository.save(user);
	}

}
