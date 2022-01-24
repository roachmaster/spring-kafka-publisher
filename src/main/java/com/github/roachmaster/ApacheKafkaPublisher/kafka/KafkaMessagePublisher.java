package com.github.roachmaster.ApacheKafkaPublisher.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaMessagePublisher implements MessagePublisher {

    @Value("${kafkaTopic}")
    public String TEST_TOPIC;

    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public void send(String message) {
        kafkaTemplate.send(TEST_TOPIC, message);
    }
}
