package com.dobval.SecureChat.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.dobval.SecureChat.model.ChatMessageDTO;
import com.dobval.SecureChat.model.Message;
import com.dobval.SecureChat.repositories.MessageRepository;

@Service
public class MessageService {

    @Autowired
    private MessageRepository MessageRepo;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    public void saveAndBroadcast(ChatMessageDTO dto, String sender) {
	System.out.println("SUCCESS: [MessageService] saveAndBroadcast called for: " 
	        + sender + " / " + dto.getContent()); //DEBUG, TODO: REMOVE
        Message msg = new Message();
        msg.setSender(sender);
        msg.setContent(dto.getContent());

        try {
            msg = MessageRepo.save(msg);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        // include sender, timestamp in the DTO for UI
        dto.setSender(sender);
        dto.setTimestamp(msg.getTimestamp());
        
        // broadcast to WebSocker subscribers
        messagingTemplate.convertAndSend("/topic/messages", dto);
    }
}
