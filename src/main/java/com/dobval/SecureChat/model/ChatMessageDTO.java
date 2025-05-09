package com.dobval.SecureChat.model;

import java.time.Instant;

import lombok.Data;

@Data
public class ChatMessageDTO {
    private String sender;
    private String content;
    private Instant timestamp;   // for UI display
}

