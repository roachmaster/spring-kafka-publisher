package com.github.roachmaster.ApacheKafkaPublisher.kafka;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaPublisherConfig {

    @Value("${kafkaTopic}")
    public String TEST_TOPIC;

    @Bean
    public NewTopic testTopic(){
        return TopicBuilder.name(TEST_TOPIC)
                .partitions(6)
                .replicas(3)
                .build();
    }

    @Bean
    public KafkaAdmin admin(){
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, "kube2:30092");
        return new KafkaAdmin(configs);
    }
}
