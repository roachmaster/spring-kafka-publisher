package com.github.roachmaster.ApacheKafkaPublisher.api;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;


@Service
public class ApacheKafkaPublisherRestApiClient {
    private final WebClient webClient;

    public ApacheKafkaPublisherRestApiClient(WebClient webClient) {
        this.webClient = webClient;
    }

    public ResponseEntity<String> publish(String message){
        return webClient
                .post()
                .uri("api/v1/publish/{message}", message)
                .retrieve()
                .toEntity(String.class)
                .block();
    }
}
