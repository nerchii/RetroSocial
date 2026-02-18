package com.retrosocial.app.controller;

import com.retrosocial.app.entity.Post;
import com.retrosocial.app.entity.User;
import com.retrosocial.app.repo.PostRepo;
import com.retrosocial.app.repo.UserRepo;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostsRestController {
    private final PostRepo postRepository;
    private final UserRepo userRepository;

    public PostsRestController(PostRepo postRepository, UserRepo userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("api/posts")
    public List<Post> getPosts(){
        return postRepository.findAll();
    }

    @PostMapping("api/posts")
    public Post createPost(@Valid @RequestBody Post post, @RequestParam(required = false) String username) {
        String effectiveUsername = (username == null || username.isBlank()) ? "guest" : username;

        User user = userRepository.findByUsername(effectiveUsername)
                .orElseGet(() -> userRepository.save(new User(effectiveUsername)));

        post.setUser(user);
        return postRepository.save(post);
    }

    @DeleteMapping("api/posts/{id}")
    public void deletePost(@PathVariable Long id) {
        postRepository.deleteById(id);
    }
}
