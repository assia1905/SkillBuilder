package com.example.micro__Learning.service;

import com.example.micro__Learning.dto.AuthRequest;
import com.example.micro__Learning.dto.UserDTO;
import com.example.micro__Learning.model.Role;
import com.example.micro__Learning.model.User;
import com.example.micro__Learning.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder; // Vérifiez que BCryptPasswordEncoder est configuré dans votre classe de configuration

    /**
     * Méthode pour enregistrer un nouvel utilisateur.
     *
     * @param authRequest Contient l'email et le mot de passe.
     * @return L'utilisateur enregistré sous forme de DTO.
     */
    @Transactional
    public UserDTO register(AuthRequest authRequest) {
        // Vérifie si l'utilisateur existe déjà
        Optional<User> existingUser = userRepository.findByEmail(authRequest.getEmail());
        if (existingUser.isPresent()) {
            throw new RuntimeException("L'utilisateur existe déjà !");
        }

        // Crée un nouvel utilisateur
        User user = new User();
        user.setEmail(authRequest.getEmail());
        user.setPassword(passwordEncoder.encode(authRequest.getPassword())); // Encrypte le mot de passe
        user.setRole(Role.USER); // Assigne le rôle par défaut USER

        // Sauvegarde l'utilisateur
        userRepository.save(user);

        // Retourne l'utilisateur sous forme de DTO
        return convertToDTO(user);
    }

    /**
     * Méthode pour se connecter avec un email et un mot de passe.
     *
     * @param authRequest Contient l'email et le mot de passe.
     * @return L'utilisateur connecté sous forme de DTO.
     */
    public UserDTO login(AuthRequest authRequest) {
        // Récupère l'utilisateur par email
        Optional<User> userOptional = userRepository.findByEmail(authRequest.getEmail());

        if (userOptional.isEmpty()) {
            throw new RuntimeException("Utilisateur non trouvé !");
        }

        User user = userOptional.get();
        // Vérifie si le mot de passe est correct
        if (!passwordEncoder.matches(authRequest.getPassword(), user.getPassword())) {
            throw new RuntimeException("Mot de passe incorrect !");
        }

        // Retourne l'utilisateur sous forme de DTO
        return convertToDTO(user);
    }

    /**
     * Méthode utilitaire pour convertir un utilisateur en DTO.
     *
     * @param user L'utilisateur à convertir.
     * @return Le DTO de l'utilisateur.
     */
    private UserDTO convertToDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setEmail(user.getEmail());
        userDTO.setRole(user.getRole());
        return userDTO;
    }
}
