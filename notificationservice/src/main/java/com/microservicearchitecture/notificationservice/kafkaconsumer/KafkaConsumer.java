package com.microservicearchitecture.notificationservice.kafkaconsumer;
import com.microservicearchitecture.notificationservice.dto.OrderCreated;
import com.microservicearchitecture.notificationservice.dto.OrderRequest1;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
//@EnableKafka
public class KafkaConsumer {

    @KafkaListener(topics = "order-request",containerFactory = "orderKafkaListenerContainerFactory")
    public void listen(OrderRequest1 record){
        System.out.println(record);
    }

    @KafkaListener(topics = "order-created", containerFactory = "orderCreatedKafkaListenerContainerFactory")
    public void listen2(OrderCreated order){
        System.out.println(order);
    }
}
