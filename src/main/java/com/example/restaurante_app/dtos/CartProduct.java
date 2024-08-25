package com.example.restaurante_app.dtos;

import com.example.restaurante_app.entities.Product;

public record CartProduct(Product product, long quantity) {
}
