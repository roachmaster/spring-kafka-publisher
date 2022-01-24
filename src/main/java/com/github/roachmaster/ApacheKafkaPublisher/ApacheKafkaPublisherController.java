package com.github.roachmaster.ApacheKafkaPublisher;

import com.github.roachmaster.ApacheKafkaPublisher.kafka.MessagePublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("api/v1/")
public class ApacheKafkaPublisherController {

    @Autowired
    private MessagePublisher messagePublisher;

    @RequestMapping(value = "publish/{message}", method = RequestMethod.POST)
    public ResponseEntity<String> publishMessage(@PathVariable String message) {
        return messagePublisher.send(message);
    }

}
