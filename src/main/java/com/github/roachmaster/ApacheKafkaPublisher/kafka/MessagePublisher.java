package com.github.roachmaster.ApacheKafkaPublisher.kafka;

import org.springframework.http.ResponseEntity;

public interface MessagePublisher {
    ResponseEntity<String> send(String message);
}
