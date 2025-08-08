package com.example.springsecurity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import com.example.springsecurity.model.BlogPost;
import com.example.springsecurity.service.BlogPostService;

import java.util.List;

@RestController
@RequestMapping("/blog")
public class BlogPostController {
    @Autowired private BlogPostService blogService;

    @PostMapping
    public BlogPost createBlog(@RequestBody BlogPost post, @AuthenticationPrincipal UserDetails userDetails) {
        return blogService.createBlog(post, userDetails.getUsername());
    }

    @PutMapping("/{id}")
    public BlogPost updateBlog(@PathVariable int id, @RequestBody BlogPost post, @AuthenticationPrincipal UserDetails userDetails) {
        return blogService.updateBlog(id, post, userDetails.getUsername());
    }

    @DeleteMapping("/{id}")
    public void deleteBlog(@PathVariable int id, @AuthenticationPrincipal UserDetails userDetails) {
        blogService.deleteBlog(id, userDetails.getUsername(), userDetails.getAuthorities().stream()
            .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN")));
    }

    @GetMapping("/feed")
    public List<BlogPost> getFeed() { return blogService.findAllBlogs(); }

    @GetMapping("/my")
    public List<BlogPost> myBlogs(@AuthenticationPrincipal UserDetails userDetails) {
        return blogService.findBlogsByUser(userDetails.getUsername());
    }

    @GetMapping("/user/{username}")
    public List<BlogPost> userBlogs(@PathVariable String username) {
        return blogService.findBlogsByUser(username);
    }
}
