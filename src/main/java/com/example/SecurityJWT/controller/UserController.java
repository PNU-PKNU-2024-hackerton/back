package com.example.SecurityJWT.controller;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.SecurityJWT.dto.UserResponseDto;
import com.example.SecurityJWT.entity.UserEntity;
import com.example.SecurityJWT.service.UserService;
import com.example.SecurityJWT.dto.ChangePasswordRequest;

@RestController
@RequestMapping("/user")

public class UserController {
	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getUser(@PathVariable("id") int id) {
		try {
			Optional<UserEntity> userEntityOptional = userService.getUserById(id);
			if (userEntityOptional.isPresent()) {
				UserEntity userEntity = userEntityOptional.get();
				UserResponseDto userResponseDTO = new UserResponseDto(userEntity.getUsername(), userEntity.getRole());
				return new ResponseEntity<>(userResponseDTO, HttpStatus.OK);
			} else {
				return new ResponseEntity<>("User Not Found", HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>("Fail to getUser", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/me")
	public ResponseEntity<?> getMyInfo() {
		try {
			UserEntity myInfo = userService.getMyInfo();
			UserResponseDto userResponseDto = new UserResponseDto(myInfo.getUsername(), myInfo.getRole());
			return new ResponseEntity<>(userResponseDto, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Fail to get myInfo", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/changepwd")
	public ResponseEntity<?> changePassword(@RequestBody ChangePasswordRequest request){
		try{
			userService.changePassword(request.getCurrentPassword(), request.getNewPassword());
			return new ResponseEntity<>("Password Change Success!", HttpStatus.OK);
		}catch	(RuntimeException e){
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
}
