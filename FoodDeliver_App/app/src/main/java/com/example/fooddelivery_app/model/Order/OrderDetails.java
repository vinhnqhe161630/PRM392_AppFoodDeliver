package com.example.fooddelivery_app.model.Order;

import java.util.UUID;
import com.google.gson.annotations.SerializedName;

public class OrderDetails {
    // "id": "8ed4e1d8-5c99-4c08-3a75-08dcf990521e",
//         "orderID": "a7c12ee7-59c1-40f3-7296-08dcf99050c7",
//         "foodID": "f0000004-0000-0000-0000-000000000004",
//         "quantity": 10,
//         "price": 30,
//         "total": 300,
//         "foodName": "Com T?m",
//         "foodImg": "com_tam.png"
    @SerializedName("id")
    private UUID id;

    @SerializedName("orderID")
    private UUID orderID;

    @SerializedName("foodID")
    private UUID foodID;

    @SerializedName("quantity")
    private int quantity;

    @SerializedName("price")
    private int price;

    @SerializedName("total")
    private int total;

    @SerializedName("foodName")
    private String foodName;

    @SerializedName("foodImg")
    private String foodImg;

    public OrderDetails(UUID id, UUID orderID, UUID foodID,
                        int quantity, int price,
                        int total, String foodName, String foodImg) {
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
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
