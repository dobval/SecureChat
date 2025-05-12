package com.dobval.SecureChat.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
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
    public void sendMessage(@Payload ChatMessageDTO message, Principal principal) {
        messageService.saveAndBroadcast(message, principal.getName());
    }
}
