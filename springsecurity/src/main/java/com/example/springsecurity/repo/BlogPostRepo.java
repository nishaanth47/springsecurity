package com.example.springsecurity.repo;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.springsecurity.model.BlogPost;
import com.example.springsecurity.model.Users;
import java.util.List;

public interface BlogPostRepo extends JpaRepository<BlogPost, Integer> {
    List<BlogPost> findByUser(Users user);
}
