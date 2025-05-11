package com.dobval.SecureChat.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dobval.SecureChat.model.User;

public interface UserRepository extends JpaRepository<User, Long>{
	//"Optional", because user might not be found
	Optional<User> findByUsername(String username);
}