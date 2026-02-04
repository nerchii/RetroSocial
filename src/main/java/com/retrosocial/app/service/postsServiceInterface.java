package com.retrosocial.app.service;

import com.retrosocial.app.entity.Post;

import java.util.List;

public interface postsServiceInterface {

    Post save(Post post);
    List<Post> findAll();
}
