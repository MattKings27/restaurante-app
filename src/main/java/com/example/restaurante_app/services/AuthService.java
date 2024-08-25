package com.example.restaurante_app.services;

import com.example.restaurante_app.entities.SessionToken;
import com.example.restaurante_app.entities.User;
import com.example.restaurante_app.exceptions.ResourceNotFoundException;
import com.example.restaurante_app.repositories.SessionTokenRepository;
import com.example.restaurante_app.repositories.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

@Service
public class AuthService implements UserDetailsService {
    private final SessionTokenRepository sessionTokenRepository;
    private final UserRepository userRepository;

    public AuthService(SessionTokenRepository sessionTokenRepository, UserRepository userRepository) {
        this.sessionTokenRepository = sessionTokenRepository;
        this.userRepository = userRepository;
    }

    public String generateToken(String email) {
        var user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        var token = UUID.randomUUID().toString();
        var tokenEntity = new SessionToken();
        tokenEntity.setToken(token);
        tokenEntity.setUser(user);
        sessionTokenRepository.save(tokenEntity);

        return token;
    }

    public Optional<User> getUserByToken(String token) {
        // TODO: Check if `findById()` is really necessary
        return sessionTokenRepository.findByToken(token).flatMap(it -> userRepository.findById(it.getUser().getId()));
    }

    public void invalidateToken(String token) {
        sessionTokenRepository.deleteByToken(token);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                Collections.singleton(new SimpleGrantedAuthority(user.getRole()))
        );
    }
}
