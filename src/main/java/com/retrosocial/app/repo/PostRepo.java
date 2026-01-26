package com.retrosocial.app.repo;

import com.retrosocial.app.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepo extends JpaRepository<Post, Long> {
}
