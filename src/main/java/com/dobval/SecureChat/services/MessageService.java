package com.dobval.SecureChat.services;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.dobval.SecureChat.model.ChatMessageDTO;
import com.dobval.SecureChat.model.Message;
import com.dobval.SecureChat.repositories.ChatMessageRepository;

@Service
public class MessageService {

    @Autowired
    private ChatMessageRepository chatMessageRepository;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    public void saveAndBroadcast(ChatMessageDTO dto, String sender) {
        Message msg = new Message();
        msg.setSender(sender);
        msg.setContent(dto.getContent());
        msg.setTimestamp(Instant.now());

        chatMessageRepository.save(msg);

        // include timestamp in the DTO for UI
        dto.setSender(sender);
        dto.setTimestamp(msg.getTimestamp());
        messagingTemplate.convertAndSend("/topic/messages", dto);
    }
}
