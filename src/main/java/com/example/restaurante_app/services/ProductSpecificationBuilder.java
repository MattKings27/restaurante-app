package com.example.restaurante_app.services;

import com.example.restaurante_app.entities.Product;
import com.example.restaurante_app.specifications.ProductSpecifications;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ProductSpecificationBuilder {
    public Specification<Product> buildSpecification(Long categoryId,
                                                     BigDecimal minPrice,
                                                     BigDecimal maxPrice) {
        return Specification.where(ProductSpecifications.hasCategoryById(categoryId))
                .and(ProductSpecifications.minPrice(minPrice))
                .and(ProductSpecifications.maxPrice(maxPrice));
    }
}
