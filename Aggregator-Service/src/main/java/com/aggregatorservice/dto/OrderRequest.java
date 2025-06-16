package com.aggregatorservice.dto;

public class OrderRequest {
    private String productId;
    private int quantity;
    private String userEmail;

    public OrderRequest(String productId, int quantity, String userEmail) {
        this.productId = productId;
        this.quantity = quantity;
        this.userEmail = userEmail;
    }

    public OrderRequest() {
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    @Override
    public String toString() {
        return "{productId='" + productId + '\'' +
                ", quantity=" + quantity +
                ", userEmail='" + userEmail + '\'' +
                '}';
    }
}

