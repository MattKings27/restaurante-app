package com.example.restaurante_app.specifications;

import com.example.restaurante_app.entities.Product;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

public class ProductSpecifications {
    public static Specification<Product> hasCategoryById(Long categoryId) {
        return (root, query, builder) -> categoryId == null ? null : builder.equal(root.get("category").get("id"), categoryId);
    }

    public static Specification<Product> minPrice(BigDecimal minAmount) {
        return (root, query, builder) -> minAmount == null ? null : builder.ge(root.get("price"), minAmount);
    }

    public static Specification<Product> maxPrice(BigDecimal maxAmount) {
        return (root, query, builder) -> maxAmount == null ? null : builder.le(root.get("price"), maxAmount);
    }
}
