package com.example.retrofitexample;

import com.google.gson.annotations.SerializedName;

public class Post {
    private int userId;

    Integer postId;
    String title;

    @SerializedName("body") //gson variables
    String text;

    public Post(int userId, String title, String text) { //not include id, restapi will do it automatically
        this.userId = userId;
        this.title = title;
        this.text = text;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
