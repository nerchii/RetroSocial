package com.retrosocial.app.controller;

import com.retrosocial.app.dto.PostWithDate;
import com.retrosocial.app.entity.Post;
import com.retrosocial.app.repo.PostRepo;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class HomeController {
    private final PostRepo postRepository;

    public HomeController(PostRepo postRepository) {
        this.postRepository = postRepository;
    }

    @GetMapping("/")
    public String home(Model model) {
        List<Post> posts = postRepository.findAll(Sort.by(Sort.Direction.DESC, "createdAt"));
        List<PostWithDate> postsWithDate = posts.stream().map(p -> new PostWithDate(p.getContent(), java.sql.Timestamp.valueOf(p.getCreatedAt())))
                .toList();


        model.addAttribute("posts", postsWithDate);
        model.addAttribute("newPost", new Post());
        return "home";
    }

    @GetMapping("/test")
    public String posts() {
        return "posts";
    }

    @PostMapping("/post")
    public String createPost(@ModelAttribute("newPost") Post post) {
        postRepository.save(post);
        return "redirect:/";
    }
}
