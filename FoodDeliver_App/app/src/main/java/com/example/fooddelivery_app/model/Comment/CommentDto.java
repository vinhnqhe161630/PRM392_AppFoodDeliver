package com.example.fooddelivery_app.model.Comment;

import com.google.gson.annotations.SerializedName;

public class CommentDto {
    private String id;
    private String content;
    private int vote;
    @SerializedName("userId")
    private String userId;
    private String userName;
    @SerializedName("commentDate")
    private String commentDate;  // New field for comment time

    private String userPicture;  // New field for user picture

    // Getters and setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public int getVote() { return vote; }
    public void setVote(int vote) { this.vote = vote; }

    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }

    public String getCommentDate() { return commentDate; }
    public void setCommentDate(String commentDate) { this.commentDate = commentDate; }

    public String getUserPicture() { return userPicture; }
    public void setUserPicture(String userPicture) { this.userPicture = userPicture; }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
