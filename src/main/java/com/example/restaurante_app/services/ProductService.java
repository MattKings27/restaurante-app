package com.example.restaurante_app.services;

import com.example.restaurante_app.dtos.NewProduct;
import com.example.restaurante_app.entities.Category;
import com.example.restaurante_app.entities.Product;
import com.example.restaurante_app.exceptions.ResourceNotFoundException;
import com.example.restaurante_app.repositories.CategoryRepository;
import com.example.restaurante_app.repositories.ProductRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    public Page<Product> getAllProducts(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    public Page<Product> getProductsByCategory(Long categoryId, Pageable pageable) {
        return productRepository.findByCategoryId(categoryId, pageable);
    }

    public Page<Product> searchProducts(String keyword, Pageable pageable) {
        return productRepository.findByNameContainingOrDescriptionContaining(keyword, keyword, pageable);
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found with id " + id));
    }

    public Product createProduct(NewProduct newProduct) {
        var product = new Product();

        Category category = categoryRepository.findById(newProduct.category().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found."));

        BeanUtils.copyProperties(newProduct, product);
        product.setCategory(category);

        return productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
