package com.example.retrofitexample;

import com.google.gson.annotations.SerializedName;

public class Comment {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
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

    public String getCmtBody() {
        return cmtBody;
    }

    public void setCmtBody(String cmtBody) {
        this.cmtBody = cmtBody;
    }

    @SerializedName("id")
    int id;

    @SerializedName("postId")
    int postId;
    String name;
    String email;

    @SerializedName("body") //gson variables
    String cmtBody;
}
