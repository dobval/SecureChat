package com.dobval.SecureChat.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.dobval.SecureChat.model.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {

    Page<Message> findAllByOrderByTimestampDesc(Pageable pageable); }