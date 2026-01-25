package com.david.project.rest.api;

import com.david.project.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final KafkaTemplate<String, User> kafkaTemplate;

    @Autowired
    public UserController(KafkaTemplate<String, User> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody User user) {
        kafkaTemplate.send("user-topic", user);
        logger.info("Produced user to Kafka topic. User: {}", user.getName());
        return ResponseEntity.ok("User creation request sent successfully.");
    }
}
