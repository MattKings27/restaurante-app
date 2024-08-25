package com.example.restaurante_app.dtos;

import jakarta.validation.constraints.NotBlank;

public record NewUser(@NotBlank String name, @NotBlank String email, @NotBlank String password) {
}
