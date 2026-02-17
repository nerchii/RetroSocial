package com.retrosocial.app.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 25)
    private String username;


    public User() {
    }

    public User(String username) {
        this.username = (username == null || username.isBlank()) ? "guest" : username;

    }

    public long getId() {
        return id;
    }

    @OneToMany(mappedBy = "user")
    private List<Post> posts = new ArrayList<>();


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                '}';
    }
}
