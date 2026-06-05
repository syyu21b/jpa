package com.example.jpa.Entity;

import java.time.LocalDateTime;

public class Post {
    private long id;
    private String title;
    private String content;
    private String author;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static Post create(String title, String content, String author) {
        Post post = new Post();
        post.title = title;
        post.content = content;
        post.author = author;
        return post;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}