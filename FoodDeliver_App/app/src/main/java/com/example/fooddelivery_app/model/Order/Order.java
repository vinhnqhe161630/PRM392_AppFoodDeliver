package com.example.fooddelivery_app.model.Order;

import java.util.UUID;

public class Order {
    private UUID id;
    private UUID userId;
    private UUID shopId;
    private String customerName;
    private String customerPhone;
    private String customerAddress;
    private String orderDate;
    private double totalAmount;
    private String status;
    private String username;
    private String shopname;
    private String shopImg; // New field added

    public Order(UUID id, UUID userId, UUID shopId,
                 String customerName, String customerPhone, String customerAddress,
                 String orderDate, double totalAmount,
                 String status, String username, String shopname, String shopImg) {
        this.id = id;
        this.userId = userId;
        this.shopId = shopId;
        this.customerName = customerName;
        this.customerPhone = customerPhone;
        this.customerAddress = customerAddress;
        this.orderDate = orderDate;
        this.totalAmount = totalAmount;
        this.status = status;
        this.username = username;
        this.shopname = shopname;
        this.shopImg = shopImg; // Initialize new field
    }

    public Order() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public UUID getShopId() {
        return shopId;
    }

    public void setShopId(UUID shopId) {
        this.shopId = shopId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getShopname() {
        return shopname;
    }

    public void setShopname(String shopname) {
        this.shopname = shopname;
    }

    public String getShopImg() {
        return shopImg;
    }

    public void setShopImg(String shopImg) {
        this.shopImg = shopImg;
    }
}
