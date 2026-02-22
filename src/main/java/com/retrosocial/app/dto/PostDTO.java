package com.retrosocial.app.dto;

public class PostDTO {
    private String content;
    private String username;

    public PostDTO(String content, String username) {
        this.content = content;
        this.username = username;
    }

    public String getContent() {
        return content;
    }

    public String getUsername() {
        return username;
    }
}
