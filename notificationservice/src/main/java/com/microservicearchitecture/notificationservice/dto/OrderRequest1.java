package com.microservicearchitecture.notificationservice.dto;

import java.util.Objects;

public class OrderRequest1 {

    private String productId;

    private int quantity;

    private String userEmail;

    public OrderRequest1() {
    }

    public String getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        OrderRequest1 that = (OrderRequest1) o;
        return quantity == that.quantity && Objects.equals(productId, that.productId) && Objects.equals(userEmail, that.userEmail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, quantity, userEmail);
    }

    @Override
    public String toString() {
        return "OrderRequest1{" +
                "productId='" + productId + '\'' +
                ", quantity=" + quantity +
                ", userEmail='" + userEmail + '\'' +
                '}';
    }
}
