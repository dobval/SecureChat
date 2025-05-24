package com.dobval.SecureChat.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;

import com.dobval.SecureChat.model.ChatMessageDTO;
import com.dobval.SecureChat.services.MessageService;

@Controller
public class ChatController {

    @Autowired
    private MessageService messageService;

    @MessageMapping("/send")
    public void sendMessage(@Payload ChatMessageDTO msgDto, Principal principal) {
        messageService.saveAndBroadcast(msgDto, principal.getName());
    }
}
