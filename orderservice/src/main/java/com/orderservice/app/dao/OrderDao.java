package com.orderservice.app.dao;

import com.orderservice.app.dto.OrderRequest;

public interface OrderDao {

    void addOrder(OrderRequest order);

    OrderRequest getOrderRequest(String productId);

    void updateOrderRequest(OrderRequest order);

    void deleteOrderRequest(String productId);
}
