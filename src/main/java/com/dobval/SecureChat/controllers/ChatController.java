package com.dobval.SecureChat.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.dobval.SecureChat.model.ChatMessageDTO;
import com.dobval.SecureChat.model.Message;
import com.dobval.SecureChat.services.MessageService;

@Controller
public class ChatController {
	private final MessageService messageService;

    @Autowired
    public ChatController(MessageService messageService) {
        this.messageService = messageService;
    }

    @MessageMapping("/send")
    @SendTo("/topic/messages")
    public ChatMessageDTO send(ChatMessageDTO dto) {
        // Optionally persist
        Message saved = messageService.save(dto.getSender(), dto.getContent());
        return new ChatMessageDTO(
            saved.getSender(),
            saved.getContent(),
            saved.getTimestamp()
        );
    }
}
