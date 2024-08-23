package com.example.restaurante_app.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
public class Product {

    @Setter
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Getter
    private String name;

    @Setter
    @Getter
    private String description;

    @Setter
    @Getter
    private Double price;

    @Setter
    @Getter
    private String image;

    @Setter
    @Getter
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    private boolean isDeleted;

    @Setter
    @Getter
    @OneToMany(mappedBy = "product")
    private List<OrderProduct> orderProducts;


    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

}