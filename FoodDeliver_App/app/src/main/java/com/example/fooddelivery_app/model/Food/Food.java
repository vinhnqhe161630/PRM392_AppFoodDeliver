package com.example.fooddelivery_app.model.Food;

import java.util.UUID;

public class Food {
    private UUID id;
    private String name;
    private double price;
    private String description;
    private String img;
    private boolean status;

    // Foreign Key
    private UUID accountId;

    private String shopName;

    public Food(UUID id, String name, double price,
                String description, String img,
                boolean status, UUID accountId, String shopName) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.img = img;
        this.status = status;
        this.accountId = accountId;
        this.shopName = shopName;
    }

    public Food() {
    }

    // Getters and setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public UUID getAccountId() {
        return accountId;
    }

    public void setAccountId(UUID accountId) {
        this.accountId = accountId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }
}
