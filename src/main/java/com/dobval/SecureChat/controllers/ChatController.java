package com.dobval.SecureChat.controllers;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.dobval.SecureChat.model.Message;

//TODO: different ChatMessage and Message entities?
@Controller
public class ChatController {
	@MessageMapping("/send")
	@SendTo("/topic/messages")
	public Message send(Message msg) { return msg; }
}
