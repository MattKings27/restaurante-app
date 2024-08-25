package com.example.restaurante_app.controllers;

import com.example.restaurante_app.dtos.NewOrder;
import com.example.restaurante_app.entities.Order;
import com.example.restaurante_app.entities.Product;
import com.example.restaurante_app.services.OrderService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public Page<Order> getAllOrders(Pageable pageable) {
        return orderService.getAllOrders(pageable);
    }

    @PostMapping
    public void placeOrder(@RequestBody @Valid NewOrder newOrder) {
        orderService.placeOrder(newOrder);
    }
}
