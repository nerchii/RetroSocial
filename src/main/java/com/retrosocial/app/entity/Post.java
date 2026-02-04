package com.retrosocial.app.entity;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 500)
    private String content;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false, length = 50)
    private String username = "guest";







    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    public Post() {
    }

    public Post(String content) {
        //za test
        this.content = content;
    }

    public Post(String content, String username) {
        //za test
        this.content = content;
        this.username = (username == null || username.isBlank()) ? "guest" : username;
    }

    public Long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = (username == null || username.isBlank()) ? "guest" : username;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", createdAt=" + createdAt +
                ", username='" + username + '\'' +
                '}';
    }
}

