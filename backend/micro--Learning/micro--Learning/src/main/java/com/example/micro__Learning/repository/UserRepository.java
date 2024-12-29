package com.example.micro__Learning.repository;
import com.example.micro__Learning.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Recherche un utilisateur par email
    Optional<User> findByEmail(String email);

    // Vérifie si un utilisateur avec cet email existe
    boolean existsByEmail(String email);
}
