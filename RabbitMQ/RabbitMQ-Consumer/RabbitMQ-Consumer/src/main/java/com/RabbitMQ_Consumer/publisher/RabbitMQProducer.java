package com.RabbitMQ_Consumer.publisher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQProducer {

    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.routing.key}")
    private String routingKey;

    private static final Logger logger = LoggerFactory.getLogger(RabbitMQProducer.class);


    //This is interface used to sending the message from producer to consumer
    private final RabbitTemplate  rabbitTemplate;

    public RabbitMQProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }


    public void sendMessage(String message) {
        logger.info("Send message -> %s", message);

        //rabbitTemplate.convertAndSend(exchange, routingKey, object);
        rabbitTemplate.convertAndSend(exchange, routingKey, message);
    }


}
