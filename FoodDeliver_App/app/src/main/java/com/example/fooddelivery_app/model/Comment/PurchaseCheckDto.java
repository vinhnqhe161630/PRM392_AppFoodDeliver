package com.example.fooddelivery_app.model.Comment;

import com.google.gson.annotations.SerializedName;

public class PurchaseCheckDto {
    @SerializedName("foodId")
    private String foodId;
    @SerializedName("hasPurchased")
    private boolean hasPurchased;

    // Getters
    public String getFoodId() {
        return foodId;
    }

    public boolean isHasPurchased() {
        return hasPurchased;
    }

    public void setHasPurchased(boolean hasPurchased) {
        this.hasPurchased = hasPurchased;
    }

    public void setFoodId(String foodId) {
        this.foodId = foodId;
    }
}

