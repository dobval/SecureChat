package com.dobval.SecureChat.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.dobval.SecureChat.entities.User;

public interface UserRepository extends JpaRepository<User, Long>{} //TODO: Long?