package com.example.restaurante_app.repositories;

import com.example.restaurante_app.entities.OrderProduct;
import com.example.restaurante_app.entities.ids.OrderProductId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderProductRepository extends JpaRepository<OrderProduct, OrderProductId> {
    List<OrderProduct> findByOrderId(long orderId);
}
