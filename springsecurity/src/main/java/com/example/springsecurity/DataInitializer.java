package com.example.springsecurity;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.springsecurity.repo.RoleRepo;
import com.example.springsecurity.model.Role;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private RoleRepo roleRepo;

    @Override
    public void run(String... args) throws Exception {
        if (roleRepo.findByName("USER") == null) {
            roleRepo.save(new Role("USER"));
        }
        if (roleRepo.findByName("ADMIN") == null) {
            roleRepo.save(new Role("ADMIN"));
        }
    }
}
