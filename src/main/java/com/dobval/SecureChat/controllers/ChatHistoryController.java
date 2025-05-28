package com.dobval.SecureChat.controllers;

import com.dobval.SecureChat.model.ChatMessageDTO;
import com.dobval.SecureChat.repositories.MessageRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/messages")
@PreAuthorize("isAuthenticated()")
public class ChatHistoryController {

    @Autowired
    private MessageRepository messageRepository;

    @GetMapping
    public Page<ChatMessageDTO> getLastMessages(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
	Pageable pageable = PageRequest.of(page, size, Sort.by("timestamp").descending());
        return messageRepository.findAll(pageable)
        	.map(msg -> new ChatMessageDTO(msg.getSender(), msg.getContent(), msg.getTimestamp()));
    }

}
