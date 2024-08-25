package com.example.restaurante_app.controllers;

import com.example.restaurante_app.dtos.NewProduct;
import com.example.restaurante_app.entities.Product;
import com.example.restaurante_app.services.ProductService;
import com.example.restaurante_app.services.ProductSpecificationBuilder;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;
    private final ProductSpecificationBuilder productSpecificationBuilder;

    public ProductController(ProductService productService, ProductSpecificationBuilder productSpecificationBuilder) {
        this.productService = productService;
        this.productSpecificationBuilder = productSpecificationBuilder;
    }

    @GetMapping
    public Page<Product> getAllProducts(@RequestParam(value = "category", required = false) Long category,
                                        @RequestParam(value = "price_le", required = false) BigDecimal minPrice,
                                        @RequestParam(value = "price_ge", required = false) BigDecimal maxPrice,
                                        Pageable pageable) {
        var spec = productSpecificationBuilder.buildSpecification(category, minPrice, maxPrice);
        return productService.getAllProducts(spec, pageable);
    }

    @GetMapping("/category/{categoryId}")
    public Page<Product> getProductsByCategory(@PathVariable Long categoryId, Pageable pageable) {
        return productService.getProductsByCategory(categoryId, pageable);
    }

    @GetMapping("/search")
    public Page<Product> searchProducts(@RequestParam @Valid @NotBlank String keyword, Pageable pageable) {
        return productService.searchProducts(keyword, pageable);
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @PostMapping
    public Product createProduct(@RequestBody @Valid NewProduct newProduct) {
        return productService.createProduct(newProduct);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }
}
