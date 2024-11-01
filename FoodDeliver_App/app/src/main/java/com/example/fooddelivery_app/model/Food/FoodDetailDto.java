package com.example.fooddelivery_app.model.Food;



import com.example.fooddelivery_app.model.Comment.Comment;

import java.util.List;

public class FoodDetailDto {
    private String name;
    private String description;
    private String img;
    private float price;
    private AccountDto account;
    private List<CommentDto> comments;

    // Getters and Setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public AccountDto getAccount() {
        return account;
    }

    public void setAccount(AccountDto account) {
        this.account = account;
    }

    public List<CommentDto> getComments() {
        return comments;
    }

    public void setComments(List<CommentDto> comments) {
        this.comments = comments;
    }

    public static class AccountDto {
        private String name;
        private String img;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }
    }

    public static class CommentDto {
        private String content;
        private int vote;
        private String userName; // Optional, if needed

        // Getters and Setters

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getVote() {
            return vote;
        }

        public void setVote(int vote) {
            this.vote = vote;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }
    }
    public double calculateAverageRating() {
        if (comments == null || comments.isEmpty()) {
            return 0; // No comments, so rating is 0
        }

        double totalRating = 0;
        for (CommentDto comment : comments) {
            totalRating += comment.getVote(); // Assume Comment has a getRating method
        }

        return totalRating / comments.size(); // Return the average rating
    }
}

