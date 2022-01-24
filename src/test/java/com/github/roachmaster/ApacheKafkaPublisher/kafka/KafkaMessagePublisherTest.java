package com.github.roachmaster.ApacheKafkaPublisher.kafka;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class KafkaMessagePublisherTest {

    @Mock
    KafkaTemplate<String, String> kafkaTemplate;

    @InjectMocks
    KafkaMessagePublisher uut;

    @Test
    void send() {
        String messageToPublish = "test";
        ResponseEntity<String> response = uut.send(messageToPublish);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(Objects.requireNonNull(response.getBody()).contains(messageToPublish));
    }
}