package com.github.roachmaster.ApacheKafkaPublisher;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ApacheKafkaPublisherControllerTest {

    @Autowired
    ApacheKafkaPublisherController uut;

    @Test
    void publishMessage() {
        String messageToPublish = "test";
        ResponseEntity<String> response = uut.publishMessage(messageToPublish);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody().contains(messageToPublish));
    }
}