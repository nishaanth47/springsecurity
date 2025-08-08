package com.example.springsecurity.model;

import jakarta.persistence.*;

@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true)
    private String name; // "USER", "ADMIN"

    // Constructors, getters, setters
    public Role() {}
    public Role(String name) { this.name = name; }
    public int getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}
