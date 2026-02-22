package com.retrosocial.app.controller;

import com.retrosocial.app.dto.PostDTO;
import com.retrosocial.app.dto.PostWithDateDTO;
import com.retrosocial.app.service.PostsServiceInterface;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostsRestController {
    PostsServiceInterface postService;

    public PostsRestController(PostsServiceInterface postService) {
        this.postService = postService;
    }

    @GetMapping("api/posts")
    public List<PostWithDateDTO> getPosts(){
        return postService.findAll();
    }

    @PostMapping("api/posts")
    public PostWithDateDTO createPost(@RequestBody PostDTO post, @RequestParam(defaultValue = "guest", required = false) String username) {
        return postService.save(post, username);
    }

    @DeleteMapping("api/posts/{id}")
    public void deletePost(@PathVariable Long id) {
        postService.deleteById(id);
    }
}
