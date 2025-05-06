package com.dobval.SecureChat.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.dobval.SecureChat.services.KafkaProducerService;

@RestController
public class MessageController {

    private final KafkaProducerService producerService;

    @Autowired
    public MessageController(KafkaProducerService producerService) {
        this.producerService = producerService;
    }

    @PostMapping("/send")
    public ResponseEntity<String> sendMessage(@RequestParam String message) {
        try {
            producerService.sendMessage("myTopic", message);
            return ResponseEntity.ok("Message sent to Kafka topic\n");
        } catch (Exception e) {
            // Log error (omitted) and return 500 with custom message
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to send message: " + e.getMessage());
        }
    }
}
