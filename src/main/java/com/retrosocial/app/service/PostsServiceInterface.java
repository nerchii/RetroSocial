package com.retrosocial.app.service;

import com.retrosocial.app.dto.PostDTO;
import com.retrosocial.app.dto.PostWithDateDTO;
import java.util.List;

public interface PostsServiceInterface {

    PostWithDateDTO save(PostDTO post, String username);
    List<PostWithDateDTO> findAll();
    void deleteById(Long id);
}
