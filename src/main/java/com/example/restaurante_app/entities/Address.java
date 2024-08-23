package com.example.restaurante_app.entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
public class Address {

    @Setter
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String address;

    private boolean isDeleted;


    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
}