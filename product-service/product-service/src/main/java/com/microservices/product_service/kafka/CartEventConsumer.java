package com.microservices.product_service.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class CartEventConsumer {

    @KafkaListener(topics = "cart-events", groupId = "product-service-group")
    public void consume(String message) {

        System.out.println("Kafka Event Received: " + message);

    }
}