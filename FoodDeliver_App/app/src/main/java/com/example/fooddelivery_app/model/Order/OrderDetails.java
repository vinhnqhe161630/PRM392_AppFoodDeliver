package com.example.fooddelivery_app.model.Order;

import java.util.UUID;

public class OrderDetails {

    private UUID id;

    // Foreign Keys
    private UUID orderID;
    private UUID foodID;

    private int quantity;
    private double price;
    private double total;

    private String foodName;  // Nullable fields can be directly set to null
    private String foodImg;

    public OrderDetails(UUID id, UUID orderID, UUID foodID,
                        int quantity, double price,
                        double total, String foodName, String foodImg) {
        this.id = id;
        this.orderID = orderID;
        this.foodID = foodID;
        this.quantity = quantity;
        this.price = price;
        this.total = total;
        this.foodName = foodName;
        this.foodImg = foodImg;
    }

    public OrderDetails() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getOrderID() {
        return orderID;
    }

    public void setOrderID(UUID orderID) {
        this.orderID = orderID;
    }

    public UUID getFoodID() {
        return foodID;
    }

    public void setFoodID(UUID foodID) {
        this.foodID = foodID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getFoodImg() {
        return foodImg;
    }

    public void setFoodImg(String foodImg) {
        this.foodImg = foodImg;
    }
}
