package com.example.SecurityJWT.service;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.SecurityJWT.dto.CustomUserDetails;
import com.example.SecurityJWT.entity.UserEntity;
import com.example.SecurityJWT.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	private final UserRepository userRepository;

	public CustomUserDetailsService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<UserEntity> userData = userRepository.findByUsername(username);

		if (userData.isPresent()) {
			return new CustomUserDetails(userData.orElse(null));
		}

		return null;
	}
}
