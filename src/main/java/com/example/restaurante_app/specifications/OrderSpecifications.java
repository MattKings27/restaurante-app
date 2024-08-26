package com.example.restaurante_app.specifications;

import com.example.restaurante_app.entities.Order;
import com.example.restaurante_app.entities.Product;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class OrderSpecifications {
    public static Specification<Order> hasCustomerName(String customerName) {
        return (root, query, builder) -> customerName == null ? null : builder.equal(root.get("customerName"), customerName);
    }

    public static Specification<Order> hasAddress(String address) {
        return (root, query, builder) -> address == null ? null : builder.equal(root.get("address"), address);
    }

    public static Specification<Order> hasStatus(String status) {
        return (root, query, builder) -> status == null ? null : builder.equal(root.get("status"), status);
    }

    public static Specification<Order> startingCreatedAt(LocalDateTime startingTime) {
        return (root, query, builder) -> startingTime == null ? null : builder.greaterThanOrEqualTo(root.get("createdAt"), startingTime);
    }

    public static Specification<Order> endingCreatedAt(LocalDateTime endingTime) {
        return (root, query, builder) -> endingTime == null ? null : builder.lessThanOrEqualTo(root.get("createdAt"), endingTime);
    }
}
