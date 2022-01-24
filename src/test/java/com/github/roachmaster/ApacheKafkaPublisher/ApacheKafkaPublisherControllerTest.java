package com.github.roachmaster.ApacheKafkaPublisher;

import com.github.roachmaster.ApacheKafkaPublisher.kafka.MessagePublisher;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
class ApacheKafkaPublisherControllerTest {
    @Mock
    MessagePublisher messagePublisher;

    @InjectMocks
    ApacheKafkaPublisherController uut;

    @Test
    void publishMessage() {
        String messageToPublish = "test";
        ResponseEntity<String> response = uut.publishMessage(messageToPublish);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody().contains(messageToPublish));
    }
}