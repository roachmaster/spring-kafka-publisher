package com.github.roachmaster.ApacheKafkaPublisher.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaPublisherConfig {

    @Bean
    public NewTopic testTopic(){
        return TopicBuilder.name("testTopic")
                .partitions(6)
                .replicas(3)
                .build();
    }
}
