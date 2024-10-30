package com.example.fooddelivery_app.model.Order;

import java.util.UUID;

public class AddOrderDetails {
    private UUID orderID;
    private UUID foodID;

    private int quantity;
    private double price;
    private double total;

    public AddOrderDetails(UUID orderID, UUID foodID, int quantity, double price, double total) {
        this.orderID = orderID;
        this.foodID = foodID;
        this.quantity = quantity;
        this.price = price;
        this.total = total;
    }

    public AddOrderDetails() {
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
}
