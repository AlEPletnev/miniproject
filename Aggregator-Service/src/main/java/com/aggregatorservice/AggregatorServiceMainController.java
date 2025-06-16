package com.aggregatorservice;

import com.aggregatorservice.dto.OrderRequest;
import com.aggregatorservice.kafkaproducer.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

@RestController
@RequestMapping("api/orders")
public class AggregatorServiceMainController {

    private final KafkaProducer kafkaProducer;

    @Autowired
    public AggregatorServiceMainController(KafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }

    @PostMapping("/process")
    public ResponseEntity<String> process(@RequestBody OrderRequest request){
        //синхронно вызывает Order Service по REST (создание/обновление заказа)
        WebClient webClient = WebClient.create("http://order-service:9091");
        String responseBody = webClient.post()
                .uri("/api/orders")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(request)
                .retrieve()
                .bodyToMono(String.class)
                .block();
        System.out.println(responseBody);
        kafkaProducer.sendMessage("order-request", request);
        return ResponseEntity.ok(request.toString());
    }

    @GetMapping("/test")
    public String test(){
        return "Test app";
    }

}