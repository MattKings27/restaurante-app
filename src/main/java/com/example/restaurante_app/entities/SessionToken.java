package com.example.restaurante_app.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "session_tokens")
public class SessionToken implements Serializable {
    @Serial
    private static final long serialVersionUID = 1;

    @Id
    private String token;

    @ManyToOne()
    @JoinColumn(nullable = false)
    private User user;

    @Column(nullable = false, insertable = false)
    private LocalDateTime createdAt;
}
