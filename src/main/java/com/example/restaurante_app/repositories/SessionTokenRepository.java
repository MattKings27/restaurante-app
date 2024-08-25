package com.example.restaurante_app.repositories;

import com.example.restaurante_app.entities.SessionToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SessionTokenRepository extends JpaRepository<SessionToken, String> {
    Optional<SessionToken> findByToken(String token);

    void deleteByToken(String token);
}
