package com.example.restaurante_app.dtos;

import jakarta.validation.constraints.Min;

public record NewOrderProduct(@Min(1) long id, @Min(1) long quantity) {
}
