package com.dobval.SecureChat.controllers;

import com.dobval.SecureChat.services.KafkaProducerService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//TODO: fix build error
@WebMvcTest(MessageController.class)
class MessageControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    KafkaProducerService producerService;

    @Test
    void sendMessage_successReturns200() throws Exception {
        // Arrange: mock doNothing on producerService
        Mockito.doNothing().when(producerService).sendMessage(eq("myTopic"), eq("hello"));

        // Act & Assert: perform POST and expect 200
        mockMvc.perform(post("/send")
                .param("message", "hello")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED))
            .andExpect(status().isOk())
            .andExpect(content().string("Message sent to Kafka topic\n"));

        // Verify interaction
        verify(producerService).sendMessage("myTopic", "hello");
    }

    @Test
    void sendMessage_failureReturns500() throws Exception {
        // Arrange: mock exception
        doThrow(new RuntimeException("Kafka down"))
            .when(producerService).sendMessage(anyString(), anyString());

        // Act & Assert: perform POST and expect 500 with error message
        mockMvc.perform(post("/send")
                .param("message", "oops")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED))
            .andExpect(status().isInternalServerError())
            .andExpect(content().string("Failed to send message: Kafka down"));

        // Verify interaction happened once
        verify(producerService).sendMessage("myTopic", "oops");
    }
}
