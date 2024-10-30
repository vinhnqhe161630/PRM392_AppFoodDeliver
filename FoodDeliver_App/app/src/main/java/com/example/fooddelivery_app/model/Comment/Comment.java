package com.example.fooddelivery_app.model.Comment;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Comment {
    @SerializedName("id")
    private String id;
    @SerializedName("content")
    private String content;
    @SerializedName("vote")
    private int vote;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public double getRating() {
        return vote;
    }
}
