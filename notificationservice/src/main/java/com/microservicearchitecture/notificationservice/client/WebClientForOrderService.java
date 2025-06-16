package com.microservicearchitecture.notificationservice.client;

import com.microservicearchitecture.notificationservice.dto.OrderRequest1;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class WebClientForOrderService {

    public OrderRequest1 getOrderByProductId(String productId){
        WebClient webClient = WebClient.create("http://localhost:9091");
        OrderRequest1 response = webClient.get()
                .uri("/api/orders/{id}", productId)
                .retrieve()
                .bodyToMono(OrderRequest1.class)
                .block();
        return response;
    }
}
