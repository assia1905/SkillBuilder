package com.example.micro__Learning.dto;

import com.example.micro__Learning.model.Role;

public class UserDTO {

    private Long id;
    private String email;
    private String password; // Ajout de l'attribut password
    private Role role;

    // Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() { // Getter pour password
        return password;
    }

    public void setPassword(String password) { // Setter pour password
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
