package com.example.SecurityJWT.repository;


import com.example.SecurityJWT.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<UserEntity, Integer> {

	Boolean existsByUsername(String username);
}