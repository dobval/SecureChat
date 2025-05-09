package com.dobval.SecureChat.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.dobval.SecureChat.model.Message;
import com.dobval.SecureChat.repositories.ChatMessageRepository;

public class MessageService {
    @Autowired private ChatMessageRepository repo;
    public Message save(String sender, String content) {
    	Message m = new Message();
        m.setSender(sender);
        m.setContent(content);
        return repo.save(m);
    }
}