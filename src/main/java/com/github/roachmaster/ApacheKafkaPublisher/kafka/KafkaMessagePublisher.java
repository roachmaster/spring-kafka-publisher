package com.github.roachmaster.ApacheKafkaPublisher.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaMessagePublisher implements MessagePublisher {
    private static final Logger logger = LoggerFactory.getLogger(KafkaMessagePublisher.class);

    public final String TEST_TOPIC;
    public final KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public KafkaMessagePublisher(KafkaTemplate<String, String> kafkaTemplate,
                                 @Value("${kafkaTopic}") String TEST_TOPIC) {
        this.kafkaTemplate = kafkaTemplate;
        this.TEST_TOPIC = TEST_TOPIC;
    }

    @Override
    public ResponseEntity<String> send(String message) {
        String messageToPublish = "Publishing the following message: " + message;
        logger.info(messageToPublish);
        kafkaTemplate.send(TEST_TOPIC, message);
        return ResponseEntity.ok(messageToPublish);
    }
}
