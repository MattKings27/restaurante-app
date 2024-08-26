package com.example.restaurante_app.repositories;

import com.example.restaurante_app.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {
    Page<Product> findByCategoryId(Long categoryId, Pageable pageable);

    @Query(value = "SELECT * FROM products p WHERE p.id = :id", nativeQuery = true)
    Product getAnyProductById(@Param("id") Long productId);

    Page<Product> findByNameContainingOrDescriptionContaining(String name, String description, Pageable pageable);
}
