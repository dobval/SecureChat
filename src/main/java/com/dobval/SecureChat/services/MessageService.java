package com.dobval.SecureChat.services;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dobval.SecureChat.model.ChatMessageDTO;
import com.dobval.SecureChat.model.Message;
import com.dobval.SecureChat.repositories.ChatMessageRepository;

@Service
public class MessageService {
    @Autowired private ChatMessageRepository repo;
    public Message save(String sender, String content) {
    	Message m = new Message();
        m.setSender(sender);
        m.setContent(content);
        return repo.save(m);
    }
    
    public void saveAndBroadcast(ChatMessageDTO dto, String sender) {
        Message msg = new Message();
        msg.setSender(sender);
        msg.setContent(dto.getContent());
        msg.setTimestamp(Instant.now());

        repo.save(msg);
        repo.convertAndSend("/topic/messages", msg);
        //TODO: fix
        //TODO: Docker Compose setup Postgre!
    }

}