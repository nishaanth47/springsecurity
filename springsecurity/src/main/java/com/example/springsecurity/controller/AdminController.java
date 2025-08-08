package com.example.springsecurity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.springsecurity.repo.UserRepo;
import com.example.springsecurity.repo.BlogPostRepo;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired private UserRepo userRepo;
    @Autowired private BlogPostRepo blogPostRepo;

    @DeleteMapping("/user/{id}")
    public void deleteUser(@PathVariable int id) {
        userRepo.deleteById(id);
    }

    @DeleteMapping("/blog/{id}")
    public void adminDeleteBlog(@PathVariable int id) {
        blogPostRepo.deleteById(id);
    }
}
