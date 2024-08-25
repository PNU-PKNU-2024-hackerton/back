package com.example.SecurityJWT.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.SecurityJWT.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

	Boolean existsByUsername(String username);

	Optional<UserEntity> findByUsername(String username);
}