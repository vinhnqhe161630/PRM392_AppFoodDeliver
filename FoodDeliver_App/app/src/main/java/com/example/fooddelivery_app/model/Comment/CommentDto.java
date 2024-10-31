package com.example.fooddelivery_app.model.Comment;



public class CommentDto {
    private String id;
    private String content;
    private int vote;
    private String userName;

    // Getters and setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public int getVote() { return vote; }
    public void setVote(int vote) { this.vote = vote; }

    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }
}
