package com.aggregatorservice.kafkaproducer;

import com.aggregatorservice.dto.OrderRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaProducer {

    private final KafkaTemplate<String, OrderRequest> kafkaTemplate;

    @Autowired
    public KafkaProducer(KafkaTemplate<String, OrderRequest> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String topic, OrderRequest orderRequest){
        kafkaTemplate.send(topic,orderRequest);
    }
}
