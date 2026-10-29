package com.RabbitMQ_Consumer.Controller;


import com.RabbitMQ_Consumer.dto.User;
import com.RabbitMQ_Consumer.publisher.RabbitMQJsonProducer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/json")
public class MessageJsonController {

    private final RabbitMQJsonProducer rabbitMQJsonProducer;

    public MessageJsonController(RabbitMQJsonProducer rabbitMQJsonProducer) {
        this.rabbitMQJsonProducer = rabbitMQJsonProducer;
    }


    @PostMapping("/publish")
    public ResponseEntity<String> publishJsonMessage(@RequestBody User user) {

        rabbitMQJsonProducer.publishJsonMessage(user);
        return ResponseEntity.ok("Json Message published by RabbitMQ......");
    }
}
