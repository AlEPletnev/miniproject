package com.orderservice.app.dto;

public record OrderDescription(

        String productId,

        int quantity,

        String userEmail,

        StatusOrder status)
{}
