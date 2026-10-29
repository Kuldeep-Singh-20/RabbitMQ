package com.RabbitMQ_Consumer.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.JacksonJsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Value("${rabbitmq.queue.name}")
    private String queue;


    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.routing.key}")
    private String routingKey;


    @Value("${rabbitmq.json.queue.name}")
    private String jsonQueue;

    @Value("${rabbitmq.routing.json.key}")
    private String routingJsonKey;

    @Value("${rabbitmq.json.exchange.name}")
    private String jsonExchange;

    //create spring bean rabbitMQ for queue
    @Bean
    public Queue queue() {
        return new Queue(queue);
    }


    @Bean
    public TopicExchange  exchange() {
        return new TopicExchange(exchange);
    }

    @Bean
    public TopicExchange jsonExchange() {
        return new TopicExchange(jsonExchange);
    }



    //binding b/w  queue and exchange using routing key
    @Bean
    public Binding binding() {

        return BindingBuilder
                .bind(queue())
                .to(exchange())
                .with(routingKey);
    }

    @Bean
    public Queue jsonQueue(){
        return new Queue(jsonQueue);
    }

    //binding b/w  json queue and exchange using routing key
    @Bean
    public Binding jsonBinding() {

        return BindingBuilder
                .bind(jsonQueue())
                .to(exchange())
                .with(routingJsonKey);
    }

    @Bean
    public MessageConverter messageConverter() {
        return new JacksonJsonMessageConverter();
    }


    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {

        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter());
        return rabbitTemplate;
    }
}
