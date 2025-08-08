package com.example.springsecurity.repo;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.springsecurity.model.Role;

public interface RoleRepo extends JpaRepository<Role, Integer> {
    Role findByName(String name);
}
