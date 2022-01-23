package com.github.roachmaster.ApacheKafkaPublisher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("api/v1/")
public class ApacheKafkaPublisherController {
    public static Logger logger = LoggerFactory.getLogger(ApacheKafkaPublisherController.class);

    @RequestMapping(value = "publish/{message}", method = RequestMethod.POST)
    public ResponseEntity<String> publishMessage(@PathVariable String message) {
        String messageToPublish = "Publishing the following message: " + message;
        logger.info(messageToPublish);
        return ResponseEntity.ok(messageToPublish);
    }

}
