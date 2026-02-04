package com.retrosocial.app.service;

import com.retrosocial.app.entity.Post;
import com.retrosocial.app.repo.PostRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class postsService implements postsServiceInterface{
    private final PostRepo postRepository;

    public postsService(PostRepo postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public Post save(Post post) {
        if (post.getContent() == null || post.getContent().isBlank()) {
            throw new IllegalArgumentException("Post content cannot be empty");
        }
        return postRepository.save(post);
    }

    @Override
    public List<Post> findAll() {
        return postRepository.findAll().stream().toList();
    }
}
