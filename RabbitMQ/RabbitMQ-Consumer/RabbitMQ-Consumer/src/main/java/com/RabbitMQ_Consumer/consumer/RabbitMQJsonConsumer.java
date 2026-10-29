package com.RabbitMQ_Consumer.consumer;

import com.RabbitMQ_Consumer.dto.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;


@Service
public class RabbitMQJsonConsumer {

    private static final Logger logger =
            LoggerFactory.getLogger(RabbitMQJsonConsumer.class);

    @RabbitListener(queues = "${rabbitmq.json.queue.name}")
    public void consumeJsonMessage(User user) {

        logger.info(String.format
                ("Received json message from queue: %s", user.toString()));

    }


}

