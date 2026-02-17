package com.retrosocial.app.controller;

import com.retrosocial.app.entity.Post;
import com.retrosocial.app.entity.User;
import com.retrosocial.app.repo.PostRepo;
import com.retrosocial.app.repo.UserRepo;
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
    public Post createPost(@RequestBody Post post) {
        // For now, REST-created posts are attributed to a default "guest" user
        User user = userRepository.findByUsername("guest")
                .orElseGet(() -> userRepository.save(new User("guest")));

        post.setUser(user);
        return postRepository.save(post);
    }

    @DeleteMapping("api/posts/{id}")
    public void deletePost(@PathVariable Long id) {
        postRepository.deleteById(id);
    }
}
