package com.dobval.SecureChat.services;

import org.junit.jupiter.api.*;
import java.io.*;

class KafkaConsumerServiceTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private PrintStream originalOut;

    private KafkaConsumerService consumerService = new KafkaConsumerService();

    @BeforeEach
    void setUpStreams() {
        originalOut = System.out;
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    void listen_printsReceivedMessage() {
        // Act
        consumerService.listen("testMessage");

        // Assert: printed line contains the message
        String output = outContent.toString().trim();
        Assertions.assertTrue(output.contains("Received message in group 'myGroup': testMessage"));
    }
}
