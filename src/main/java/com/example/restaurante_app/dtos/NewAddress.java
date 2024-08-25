package com.example.restaurante_app.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record NewAddress(@Min(1) long userId, @NotBlank String address) {
}
