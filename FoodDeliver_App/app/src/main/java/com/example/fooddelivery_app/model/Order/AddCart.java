package com.example.fooddelivery_app.model.Order;

import java.util.UUID;
import com.google.gson.annotations.SerializedName;

public class AddCart {
    @SerializedName("userId")
    private UUID userId;

    @SerializedName("foodId")
    private UUID foodId;

    @SerializedName("quantity")
    private int quantity;

    // Constructor
    public AddCart(UUID userId, UUID foodId, int quantity) {
        this.userId = userId;
        this.foodId = foodId;
        this.quantity = quantity;
    }

    public AddCart() {

    }

    // Getters
    public UUID getUserId() {
        return userId;
    }

    public UUID getFoodId() {
        return foodId;
    }

    public int getQuantity() {
        return quantity;
    }

    // Setters (optional, if you need to modify values)
    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public void setFoodId(UUID foodId) {
        this.foodId = foodId;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
