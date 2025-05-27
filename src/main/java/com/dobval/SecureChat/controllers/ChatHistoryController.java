package com.dobval.SecureChat.controllers;

import com.dobval.SecureChat.model.Message;
import com.dobval.SecureChat.repositories.MessageRepository;

import jakarta.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/messages")
public class ChatHistoryController {

    @Autowired
    private MessageRepository messageRepository;

    @GetMapping
    public Page<Message> getLastMessages(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size
    ) {
	Pageable pageable = PageRequest.of(page, size, Sort.by("timestamp").descending());
        return messageRepository.findAll(pageable);
    }
    
    @PostConstruct //DEBUG, TODO:REMOVE
    public void init() {
        System.out.println("✅ ChatHistoryController is active");
    }

}
