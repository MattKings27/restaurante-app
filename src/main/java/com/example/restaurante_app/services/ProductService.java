package com.example.restaurante_app.services;

import com.example.restaurante_app.dtos.EditProduct;
import com.example.restaurante_app.dtos.NewProduct;
import com.example.restaurante_app.entities.Product;
import com.example.restaurante_app.exceptions.ResourceNotFoundException;
import com.example.restaurante_app.repositories.ProductRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
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
        BeanUtils.copyProperties(newProduct, product);
        return productRepository.save(product);
    }

    public Product updateProduct(Long id, EditProduct editProduct) {
        var product = productRepository.getReferenceById(id);
        BeanUtils.copyProperties(editProduct, product);
        return productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
