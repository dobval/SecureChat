package com.dobval.SecureChat.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.dobval.SecureChat.entities.ChatMessage;
import com.dobval.SecureChat.repositories.ChatMessageRepository;

public class MessageService {
    @Autowired private ChatMessageRepository repo;
    public ChatMessage save(String sender, String content) {
    	ChatMessage m = new ChatMessage();
        m.setSender(sender);
        m.setContent(content);
        return repo.save(m);
    }
}