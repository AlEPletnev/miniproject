package com.microservicearchitecture.notificationservice.dto;

public record OrderCreated(
        String productId,

        int quantity,

        String userEmail,

        String buyerId,

        StatusOrder status
)
{}
