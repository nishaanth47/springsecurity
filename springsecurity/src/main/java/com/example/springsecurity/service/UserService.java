package com.example.springsecurity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.springsecurity.model.Users;
import com.example.springsecurity.model.Role;
import com.example.springsecurity.repo.RoleRepo;
import com.example.springsecurity.repo.UserRepo;
import java.util.Set;

@Service
public class UserService {
    @Autowired private UserRepo userRepo;
    @Autowired private RoleRepo roleRepo;
    @Autowired private JWTService jwtService;
    @Autowired AuthenticationManager authManager;
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public Users register(Users user, String roleName) {
        user.setPassword(encoder.encode(user.getPassword()));

        // Default to USER if roleName is null/empty
        String roleKey = (roleName == null || roleName.isBlank()) ? "USER" : roleName.toUpperCase();

        // Try to fetch role
        Role role = roleRepo.findByName(roleKey);

        // If role not found, create it dynamically
        if (role == null) {
            role = new Role(roleKey);
            role = roleRepo.save(role);
        }

        user.setRoles(Set.of(role));
        return userRepo.save(user);
    }


    public String login(String username, String password) {
        Authentication authentication = authManager.authenticate(
            new UsernamePasswordAuthenticationToken(username, password));
        if(authentication.isAuthenticated()) {
            return jwtService.generateToken((org.springframework.security.core.userdetails.UserDetails) authentication.getPrincipal());
        }
        return null;
    }
}
