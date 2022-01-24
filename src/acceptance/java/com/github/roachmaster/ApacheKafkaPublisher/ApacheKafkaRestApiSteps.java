package com.github.roachmaster.ApacheKafkaPublisher;

import com.github.roachmaster.ApacheKafkaPublisher.api.ApacheKafkaPublisherRestApiClient;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.ConfigDataApplicationContextInitializer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

@CucumberContextConfiguration
@ContextConfiguration(classes = { ApacheKafkaPublisherCucumberConfig.class },
                      initializers = { ConfigDataApplicationContextInitializer.class })
@TestPropertySource(properties = {"classpath:"})
@ActiveProfiles("bdd")
public class ApacheKafkaRestApiSteps {
    
    @Autowired
    ApacheKafkaPublisherRestApiClient apacheKafkaPublisherRestApiClient;

    Scenario scenario;

    String message;
    ResponseEntity<String> response;

    @Before
    public void setUp(Scenario scenario){
        this.scenario = scenario;
    }

    @Given("that the Apache Kafka Publisher receives a request to publish a message")
    public void thatTheApacheKafkaPublisherReceivesARequestToPublishAMessage(){
        Random random = new Random();
        message = "test:" + Math.abs(random.nextInt());
        this.scenario.log("Sending the Following Message: " + message);
    }

    @When("the Apache Kafka Publisher responds to the request with an OK")
    public void theApacheKafkaPublisherRespondsToTheRequestWithAnOK() {
        response = apacheKafkaPublisherRestApiClient.publish(message);
    }

    @Then("the Apache Kafka Publisher has successfully published a message")
    public void theApacheKafkaPublisherHasSuccessfullyPublishedAMessage() {
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
