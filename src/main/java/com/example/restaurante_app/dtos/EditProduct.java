package com.example.restaurante_app.dtos;

import jakarta.validation.constraints.NotBlank;

public record EditProduct(String name, String description, String image, String price, String category) {
}
