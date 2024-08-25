package com.example.restaurante_app.dtos;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record NewOrder(@NotBlank String customerName, @NotBlank String address, @NotEmpty @Valid List<NewOrderProduct> products) {
}
