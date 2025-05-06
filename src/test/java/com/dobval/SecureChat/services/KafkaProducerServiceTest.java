package com.dobval.SecureChat.services;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.kafka.core.KafkaTemplate;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

class KafkaProducerServiceTest {

    @Mock
    private KafkaTemplate<String, String> kafkaTemplate;

    @InjectMocks
    private KafkaProducerService producerService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void sendMessage_invokesKafkaTemplateSend() {
        // Act
        producerService.sendMessage("topicX", "msg123");

        // Assert: verify send called with correct arguments
        verify(kafkaTemplate).send(eq("topicX"), eq("msg123"));
    }
}
