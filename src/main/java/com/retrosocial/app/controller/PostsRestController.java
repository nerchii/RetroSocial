package com.retrosocial.app.controller;

import com.retrosocial.app.entity.Post;
import com.retrosocial.app.repo.PostRepo;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostsRestController {
    private final PostRepo postRepository;

    public PostsRestController(PostRepo postRepository) {
        this.postRepository = postRepository;
    }

    @GetMapping("api/posts")
    public List<Post> getPosts(){
        return postRepository.findAll();
    }

    @PostMapping("api/posts")
    public Post createPost(@RequestBody Post post) {
        return postRepository.save(post);
    }

    @DeleteMapping("api/posts/{id}")
    public void deletePost(@PathVariable Long id) {
        postRepository.deleteById(id);
    }
}
