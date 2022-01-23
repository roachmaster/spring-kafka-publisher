Feature: Testing the Behavior of the Apache Kafka Publisher's Rest Api

  Scenario: The Apache Kafka Publisher Behavior when it receives a request to publish a message
    Given that the Apache Kafka Publisher receives a request to publish a message
    When the Apache Kafka Publisher responds to the request with an OK
    Then the Apache Kafka Publisher has successfully published a message