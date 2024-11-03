package com.example.fooddelivery_app.model.Comment;

public class Blog {
    private String imageUrl;
    private String title;
    private String author;
    private String date;
    private String summary;

    public Blog(String imageUrl, String title, String author, String date, String summary) {
        this.imageUrl = imageUrl;
        this.title = title;
        this.author = author;
        this.date = date;
        this.summary = summary;
    }



    public String getImageUrl() {
        return imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getDate() {
        return date;
    }

    public String getSummary() {
        return summary;
    }
}
