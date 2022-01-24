package com.github.roachmaster.ApacheKafkaPublisher;

import com.github.roachmaster.ApacheKafkaPublisher.api.ApacheKafkaPublisherRestApiClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class ApacheKafkaPublisherCucumberConfig {
    Logger logger = LoggerFactory.getLogger(ApacheKafkaPublisherCucumberConfig.class);
    @Value("${uri}")
    public String APACHE_KAFKA_PUBLISHER_URI;

    @Bean
    public WebClient webClient(){
        logger.info("Using the Following uri: {}", APACHE_KAFKA_PUBLISHER_URI);
        return WebClient.builder().baseUrl(APACHE_KAFKA_PUBLISHER_URI).build();
    }

    @Bean
    public ApacheKafkaPublisherRestApiClient apacheKafkaPublisherRestApiClient(WebClient webClient){
        return new ApacheKafkaPublisherRestApiClient(webClient);

    }
}
