package com.github.roachmaster.ApacheKafkaPublisher;

import com.github.roachmaster.ApacheKafkaPublisher.kafka.MessagePublisher;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ApacheKafkaPublisherControllerTest {

    @Mock
    MessagePublisher messagePublisher;

    @InjectMocks
    ApacheKafkaPublisherController uut;

    @Test
    void publishMessage() {
        String messageToPublish = "test";
        when(messagePublisher.send(anyString())).thenReturn(ResponseEntity.ok(messageToPublish));
        ResponseEntity<String> response = uut.publishMessage(messageToPublish);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(Objects.requireNonNull(response.getBody()).contains(messageToPublish));
    }
}