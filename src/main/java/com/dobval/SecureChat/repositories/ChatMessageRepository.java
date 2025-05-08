package com.dobval.SecureChat.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dobval.SecureChat.entities.ChatMessage;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> { }