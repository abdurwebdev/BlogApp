package com.example.madfinal.models;

import java.io.Serializable;

/**
 * Model class representing a Blog Post.
 * Implements Serializable to pass objects between activities.
 */
public class BlogPost implements Serializable {
    private int id;
    private String title;
    private String body;
    private int userId;
    
    // Additional fields for local app usage if needed (e.g., isSaved, localTimestamp)
    private boolean isLocallySaved;

    public BlogPost(int id, String title, String body, int userId) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.userId = userId;
        this.isLocallySaved = false;
    }

    public BlogPost() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public boolean isLocallySaved() {
        return isLocallySaved;
    }

    public void setLocallySaved(boolean locallySaved) {
        isLocallySaved = locallySaved;
    }
}