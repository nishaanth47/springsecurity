package com.example.springsecurity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.springsecurity.model.Users;
import com.example.springsecurity.service.UserService;
import com.example.springsecurity.repo.UserRepo;

@RestController
public class UserController {
    @Autowired private UserService service;
    @Autowired private UserRepo userRepo;

    @PostMapping("/register")
    public Users register(@RequestBody Users user, @RequestParam(defaultValue = "USER") String role) {
        return service.register(user, role);
    }

    @PostMapping("/login")
    public String login(@RequestBody Users user) {
        String token = service.login(user.getUsername(), user.getPassword());
        if(token == null) throw new RuntimeException("Bad credentials");
        return token;
    }
}
