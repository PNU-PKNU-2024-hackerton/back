package com.example.SecurityJWT.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponseDto {

	private String username;
	private String role;

	public UserResponseDto(String username, String role){
		this.username = username;
		this.role = role;
	}
}
