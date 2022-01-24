package com.github.roachmaster.ApacheKafkaPublisher;

import com.github.roachmaster.ApacheKafkaPublisher.kafka.MessagePublisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("api/v1/")
public class ApacheKafkaPublisherController {
    private static Logger logger = LoggerFactory.getLogger(ApacheKafkaPublisherController.class);

    @Autowired
    private MessagePublisher messagePublisher;

    @RequestMapping(value = "publish/{message}", method = RequestMethod.POST)
    public ResponseEntity<String> publishMessage(@PathVariable String message) {
        String messageToPublish = "Publishing the following message: " + message;
        logger.info(messageToPublish);
        messagePublisher.send(message);
        return ResponseEntity.ok(messageToPublish);
    }

}
