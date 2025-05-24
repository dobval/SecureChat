package com.dobval.SecureChat.model;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// WebSocket/Kafka payload DTO — not persisted by JPA
@Data
@NoArgsConstructor 	// Jackson (display timestamp in html)
@AllArgsConstructor
public class ChatMessageDTO {
    private String sender;
    private String content;
    private Instant timestamp;   // for UI display
}

