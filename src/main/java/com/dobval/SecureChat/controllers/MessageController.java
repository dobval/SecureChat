package com.dobval.SecureChat.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dobval.SecureChat.services.KafkaProducerService;

@RestController
public class MessageController {

    private final KafkaProducerService producerService;

    @Autowired
    public MessageController(KafkaProducerService producerService) {
        this.producerService = producerService;
    }

    @PostMapping("/send")
    public String sendMessage(@RequestParam String message) { //TODO: Test using Postman/curl "curl -X POST "http://localhost:8080/send?message=Hello Kafka!""
        producerService.sendMessage("myTopic", message);	//TODO: write a unit test
        return "Message sent to Kafka topic";
    }
}