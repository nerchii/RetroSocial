package com.retrosocial.app.service;

import com.retrosocial.app.dto.PostDTO;
import com.retrosocial.app.dto.PostWithDateDTO;
import com.retrosocial.app.entity.Post;
import com.retrosocial.app.entity.User;
import com.retrosocial.app.repo.PostRepo;
import com.retrosocial.app.repo.UserRepo;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostsService implements PostsServiceInterface {
    private final PostRepo postRepository;
    private final UserRepo userRepository;

    public PostsService(PostRepo postRepository, UserRepo userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @Override
    public PostWithDateDTO save(PostDTO postDTO, String username) {
        if (postDTO.getContent() == null || postDTO.getContent().isBlank()) {
            throw new IllegalArgumentException("Post content cannot be empty");
        }
        User user = userRepository.findByUsername(username)
                .orElseGet(() -> userRepository.save(new User(username)));

        Post post = new Post(postDTO.getContent(), user);
        post.setUser(user);
        Post newPost = postRepository.save(post);
        System.out.println("Added post: " + newPost);
        return new PostWithDateDTO(newPost.getContent(), java.sql.Timestamp.valueOf(newPost.getCreatedAt()), newPost.getUser().getUsername());
    }

    @Override
    public List<PostWithDateDTO> findAll() {
        List<Post> posts = postRepository.findAll(Sort.by(Sort.Direction.ASC, "createdAt"));
        List<PostWithDateDTO> postsWithDate = new ArrayList<>();
        for (Post post : posts) {
            postsWithDate.add(new PostWithDateDTO(
                    post.getContent(),
                    java.sql.Timestamp.valueOf(post.getCreatedAt()),
                    post.getUser().getUsername()
            ));
        }

        System.out.printf("Found %s posts%n", postsWithDate.size());
        return postsWithDate;
    }

    @Override
    public void deleteById(Long id) {
        postRepository.deleteById(id);
    }
}
