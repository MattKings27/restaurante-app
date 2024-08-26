package com.example.restaurante_app.specifications;

import com.example.restaurante_app.entities.Order;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;

public class OrderSpecifications {
    public static Specification<Order> hasCustomerName(String customerName) {
        return (root, query, builder) -> customerName == null ? null :
                builder.like(builder.lower(root.get("customerName")), "%" + customerName.toLowerCase() + "%");
    }

    public static Specification<Order> hasAddress(String address) {
        return (root, query, builder) -> address == null ? null :
                builder.like(builder.lower(root.get("address")), "%" + address.toLowerCase() + "%");
    }

    public static Specification<Order> isStatus(String status) {
        return (root, query, builder) -> status == null ? null : builder.equal(root.get("status"), status);
    }

    public static Specification<Order> startingCreatedAt(LocalDateTime startingTime) {
        return (root, query, builder) -> startingTime == null ? null : builder.greaterThanOrEqualTo(root.get("createdAt"), startingTime);
    }

    public static Specification<Order> endingCreatedAt(LocalDateTime endingTime) {
        return (root, query, builder) -> endingTime == null ? null : builder.lessThanOrEqualTo(root.get("createdAt"), endingTime);
    }
}
