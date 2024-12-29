package com.example.micro__Learning.service;
import com.example.micro__Learning.model.User;
import com.example.micro__Learning.repository.UserRepository;
import com.example.micro__Learning.model.Role;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // Création d'un utilisateur
    public User createUser(String email, String password, String roleName) {
        if (userRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("Un utilisateur avec cet email existe déjà.");
        }

        User user = new User();
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        user.setRole(convertStringToRole(roleName));

        return userRepository.save(user);
    }

    // Récupération d'un utilisateur par ID
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    // Récupération d'un utilisateur par email
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    // Mise à jour d'un utilisateur
    public User updateUser(Long id, String email, String password, String roleName) {
        User user = userRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("Utilisateur non trouvé avec l'ID : " + id));

        user.setEmail(email);
        if (password != null && !password.isEmpty()) {
            user.setPassword(passwordEncoder.encode(password));
        }
        if (roleName != null && !roleName.isEmpty()) {
            user.setRole(convertStringToRole(roleName));
        }

        return userRepository.save(user);
    }

    // Suppression d'un utilisateur
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new IllegalArgumentException("Utilisateur non trouvé avec l'ID : " + id);
        }
        userRepository.deleteById(id);
    }

    // Méthode utilitaire pour convertir un String en Role
    private Role convertStringToRole(String roleName) {
        try {
            return Role.valueOf(roleName.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Rôle invalide : " + roleName);
        }
    }
}
