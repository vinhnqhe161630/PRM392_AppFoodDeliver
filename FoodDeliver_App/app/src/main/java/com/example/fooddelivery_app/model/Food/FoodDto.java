package com.example.fooddelivery_app.model.Food;

import com.example.fooddelivery_app.model.Comment.Comment;
import com.google.gson.annotations.SerializedName;



import java.util.List;

public class FoodDto {

    @SerializedName("id")
    private String id;
    @SerializedName("name")
    private String name;
    @SerializedName("price")
    private double price;
    @SerializedName("description")
    private String description;
    @SerializedName("img")
    private String img;
    @SerializedName("status")
    private boolean status;
    @SerializedName("comments")
    private List<Comment> comments;
    private Account account;

    // Constructor, Getters, and Setters
    public Account getAccount() {
        return account;
    }

    public static class Account {
        private String name;
        private String img;

        // Getters for Account fields
        public String getName() {
            return name;
        }

        public String getImg() {
            return img;
        }
    }
    public double calculateAverageRating() {
        if (comments == null || comments.isEmpty()) {
            return 0; // No comments, so rating is 0
        }

        double totalRating = 0;
        for (Comment comment : comments) {
            totalRating += comment.getRating(); // Assume Comment has a getRating method
        }

        return totalRating / comments.size(); // Return the average rating
    }
    // Getter và Setter
    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
