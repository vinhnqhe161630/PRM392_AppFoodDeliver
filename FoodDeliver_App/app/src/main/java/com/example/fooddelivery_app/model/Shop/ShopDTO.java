package com.example.fooddelivery_app.model.Shop;

import com.example.fooddelivery_app.model.Comment.Comment;
import com.example.fooddelivery_app.model.Food.FoodDto;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ShopDTO {
    @SerializedName("id")
    private String id;

    @SerializedName("name")
    private String name;

    @SerializedName("email")
    private String email;

    @SerializedName("pass")
    private String pass;

    @SerializedName("img")
    private String img;

    @SerializedName("phone")
    private String phone;

    @SerializedName("address")
    private String address;

    @SerializedName("role")
    private String role;

    @SerializedName("status")
    private boolean status;
    @SerializedName("foods")
    private List<FoodDto> foods; // List of FoodDto related to this shop

    public float calculateShopAverageRating() {
        if (foods == null || foods.isEmpty()) {
            return 0; // No foods, so average rating is 0
        }

        float totalRating = 0;
        int totalComments = 0;

        for (FoodDto food : foods) {
            List<Comment> comments = food.getComments();
            if (comments != null) {
                for (Comment comment : comments) {
                    totalRating += comment.getVote(); // Assume getVote() returns the rating
                    totalComments++;
                }
            }
        }

        return totalComments > 0 ? totalRating / totalComments : 0; // Return the average rating or 0 if no comments
    }
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
