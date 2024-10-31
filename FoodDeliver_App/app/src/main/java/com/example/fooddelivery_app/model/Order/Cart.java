package com.example.fooddelivery_app.model.Order;

import java.util.UUID;
import com.google.gson.annotations.SerializedName;

public class Cart {
    @SerializedName("id")
    private UUID id;

    @SerializedName("userId")
    private UUID userId;

    @SerializedName("shopId")
    private UUID shopId;

    @SerializedName("foodId")
    private UUID foodId;

    @SerializedName("quantity")
    private int quantity;

    @SerializedName("foodName")
    private String foodName;

    @SerializedName("shopName")
    private String shopName;

    @SerializedName("price")
    private int price;

    @SerializedName("foodImage")
    private String foodImage;

    // Constructor
    public Cart(UUID id, UUID userId, UUID shopId, UUID foodId, int quantity, String foodName, String shopName, int price, String foodImage) {
        this.id = id;
        this.userId = userId;
        this.shopId = shopId;
        this.foodId = foodId;
        this.quantity = quantity;
        this.foodName = foodName;
        this.shopName = shopName;
        this.price = price;
        this.foodImage = foodImage;
    }

    // Getters
    public UUID getId() {
        return id;
    }

    public UUID getUserId() {
        return userId;
    }

    public UUID getShopId() {
        return shopId;
    }

    public UUID getFoodId() {
        return foodId;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getFoodName() {
        return foodName;
    }

    public String getShopName() {
        return shopName;
    }

    public int getPrice() {
        return price;
    }

    public String getFoodImage() {
        return foodImage;
    }

    // Setters (optional, if you need to modify values)
    public void setId(UUID id) {
        this.id = id;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public void setShopId(UUID shopId) {
        this.shopId = shopId;
    }

    public void setFoodId(UUID foodId) {
        this.foodId = foodId;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setFoodImage(String foodImage) {
        this.foodImage = foodImage;
    }
}
