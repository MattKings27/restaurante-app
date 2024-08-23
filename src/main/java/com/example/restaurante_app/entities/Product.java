package com.example.restaurante_app.entities;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private Double price;

    private String image;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    private boolean isDeleted;

    @OneToMany(mappedBy = "product")
    private List<OrderProduct> orderProducts;


}