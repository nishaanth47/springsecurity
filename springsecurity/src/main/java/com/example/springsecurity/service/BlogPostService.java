package com.example.springsecurity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.springsecurity.model.BlogPost;
import com.example.springsecurity.model.Users;
import com.example.springsecurity.repo.BlogPostRepo;
import com.example.springsecurity.repo.UserRepo;

import java.util.List;

@Service
public class BlogPostService {
    @Autowired private BlogPostRepo blogRepo;
    @Autowired private UserRepo userRepo;

    public BlogPost createBlog(BlogPost post, String username) {
        Users user = userRepo.findByUsername(username);
        post.setUser(user);
        return blogRepo.save(post);
    }
    public List<BlogPost> findAllBlogs() { return blogRepo.findAll(); }
    public List<BlogPost> findBlogsByUser(String username) {
        Users user = userRepo.findByUsername(username);
        return blogRepo.findByUser(user);
    }
    public BlogPost updateBlog(int id, BlogPost data, String username) {
        BlogPost post = blogRepo.findById(id).orElseThrow();
        if(!post.getUser().getUsername().equals(username))
            throw new RuntimeException("Forbidden");
        post.setTitle(data.getTitle());
        post.setDescription(data.getDescription());
        post.setImageUrl(data.getImageUrl());
        return blogRepo.save(post);
    }
    public void deleteBlog(int id, String username, boolean isAdmin) {
        BlogPost post = blogRepo.findById(id).orElseThrow();
        if(isAdmin || post.getUser().getUsername().equals(username))
            blogRepo.delete(post);
        else throw new RuntimeException("Forbidden");
    }
}
