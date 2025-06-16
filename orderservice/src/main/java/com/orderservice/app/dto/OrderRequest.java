package com.orderservice.app.dto;

public record OrderRequest(

        String productId,

        int quantity,

        String userEmail,

        String buyerId
)
{}
