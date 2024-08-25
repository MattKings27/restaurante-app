package com.example.restaurante_app.dtos;

import jakarta.validation.constraints.NotBlank;

public record NewProduct(@NotBlank String name, @NotBlank String description, @NotBlank String image, @NotBlank String price, @NotBlank String category) {
}
