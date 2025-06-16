package com.orderservice.app.kafka;

import com.orderservice.app.dto.OrderDescription;
import com.orderservice.app.dto.OrderRequest;
import com.orderservice.app.dto.StatusOrder;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@PropertySource("kafka.properties")
public class OrderProducer {

    private final KafkaTemplate<String, OrderDescription> kafkaTemplate;

    @Value("${kafka.topic}")
    private String nameTopic;

    @Autowired
    public OrderProducer(KafkaTemplate<String, OrderDescription> kafkaTemplate){
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(OrderRequest order){
        OrderDescription orderCreated = new OrderDescription(order.productId(), order.quantity(), order.userEmail(), StatusOrder.CREATED);
        kafkaTemplate.send(this.nameTopic,orderCreated);
    }
}
