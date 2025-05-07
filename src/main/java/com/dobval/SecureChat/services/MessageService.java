package com.dobval.SecureChat.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.dobval.SecureChat.entities.Message;
import com.dobval.SecureChat.repositories.MessageRepository;

public class MessageService {
    @Autowired private MessageRepository repo;
    public Message save(String sender, String content) {
        Message m = new Message();
        m.setSender(sender);
        m.setContent(content);
        return repo.save(m);
    }
}
