package com.dobval.SecureChat.services;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    @KafkaListener(topics = "myTopic", groupId = "myGroup")
    public void listen(String message) {
        System.out.println("Received message in group 'myGroup': " + message);
    }
}
