package com.example.restaurante_app.entities.ids;

import com.example.restaurante_app.entities.Order;
import com.example.restaurante_app.entities.Product;
import jakarta.persistence.Embeddable;

@Embeddable
public record OrderProductId(Long orderId, Long productId) {
}
