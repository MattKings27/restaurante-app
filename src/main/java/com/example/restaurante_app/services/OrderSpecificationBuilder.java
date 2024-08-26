package com.example.restaurante_app.services;

import com.example.restaurante_app.entities.Order;
import com.example.restaurante_app.specifications.OrderSpecifications;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class OrderSpecificationBuilder {
    public Specification<Order> buildSpecification(String customerName,
                                                   String address,
                                                   String status,
                                                   LocalDateTime minDate,
                                                   LocalDateTime maxDate) {
        return Specification
                .where(OrderSpecifications.hasCustomerName(customerName))
                .and(OrderSpecifications.hasAddress(address))
                .and(OrderSpecifications.hasStatus(status))
                .and(OrderSpecifications.startingCreatedAt(minDate))
                .and(OrderSpecifications.endingCreatedAt(maxDate));
    }
}
