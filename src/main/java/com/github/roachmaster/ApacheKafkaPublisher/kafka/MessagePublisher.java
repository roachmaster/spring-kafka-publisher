package com.github.roachmaster.ApacheKafkaPublisher.kafka;

public interface MessagePublisher {
    void send(String message);
}
