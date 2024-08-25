package com.example.restaurante_app.dtos;

import com.example.restaurante_app.entities.Category;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record NewProduct(@NotBlank String name, @NotBlank String description, @NotBlank String image, @NotNull BigDecimal price, @NotNull Category category) {
}
