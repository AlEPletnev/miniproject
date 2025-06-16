package com.microservicearchitecture.notificationservice.dao;

import com.microservicearchitecture.notificationservice.dto.OrderRequest1;

public interface OrderDao {

    void addOrderRequest(OrderRequest1 orderRequest1);

    OrderRequest1 getOrderRequestById(String productId);

    void updateOrderRequest(OrderRequest1 orderRequest1);

    void deleteOrderRequest(String productId);
}
