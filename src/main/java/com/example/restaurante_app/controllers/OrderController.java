package com.example.restaurante_app.controllers;

import com.example.restaurante_app.dtos.CartProduct;
import com.example.restaurante_app.dtos.NewOrder;
import com.example.restaurante_app.entities.Order;
import com.example.restaurante_app.services.OrderService;
import com.example.restaurante_app.services.OrderSpecificationBuilder;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderService orderService;
    private final OrderSpecificationBuilder orderSpecificationBuilder;

    public OrderController(OrderService orderService, OrderSpecificationBuilder orderSpecificationBuilder) {
        this.orderService = orderService;
        this.orderSpecificationBuilder = orderSpecificationBuilder;
    }

    @GetMapping
    public Page<Order> getOrders(@RequestParam(value = "customer_name", required = false) String customerName,
                                 @RequestParam(value = "address", required = false) String address,
                                 @RequestParam(value = "status", required = false) String status,
                                 @RequestParam(value = "from_time", required = false) LocalDateTime minDate,
                                 @RequestParam(value = "to_time", required = false) LocalDateTime maxDate,
                                 Pageable pageable,
                                 Sort sort) {
        var spec = orderSpecificationBuilder.buildSpecification(customerName, address, status, minDate, maxDate);
        return orderService.getAllOrders(spec, sort, pageable);
    }

    @GetMapping("/{orderId}/products")
    public List<CartProduct> getProductsByOrderId(@PathVariable long orderId) {
        return orderService.getProductsByOrderId(orderId);
    }

    @PostMapping
    public void placeOrder(@RequestBody @Valid NewOrder newOrder) {
        orderService.placeOrder(newOrder);
    }
}
