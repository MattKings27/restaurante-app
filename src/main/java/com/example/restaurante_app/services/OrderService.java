package com.example.restaurante_app.services;

import com.example.restaurante_app.dtos.CartProduct;
import com.example.restaurante_app.dtos.NewOrder;
import com.example.restaurante_app.entities.Order;
import com.example.restaurante_app.entities.OrderProduct;
import com.example.restaurante_app.repositories.OrderProductRepository;
import com.example.restaurante_app.repositories.OrderRepository;
import com.example.restaurante_app.repositories.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final OrderProductRepository orderProductRepository;

    public OrderService(OrderRepository orderRepository, ProductRepository productRepository, OrderProductRepository orderProductRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.orderProductRepository = orderProductRepository;
    }

    public Page<Order> getAllOrders(Specification<Order> spec, Sort sort, Pageable pageable) {
        var pageRequest = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);
        return orderRepository.findAll(spec, pageRequest);
    }

    public List<CartProduct> getProductsByOrderId(long orderId) {
        return orderProductRepository.findByOrderId(orderId)
                .stream()
                .map(it -> new CartProduct(it.getProduct(), it.getQuantity()))
                .toList();
    }

    @Transactional
    public void placeOrder(NewOrder newOrder) {
        var order = new Order();
        BeanUtils.copyProperties(newOrder, order, "products");
        order.setStatus("received"); // TODO: This must be a database default
        order.setCreatedAt(LocalDateTime.now());
        orderRepository.save(order);

        for (var cartProduct : newOrder.products()) {
            var orderProduct = new OrderProduct();
            orderProduct.setOrder(order);
            orderProduct.setProduct(productRepository.getReferenceById(cartProduct.id()));
            orderProduct.setQuantity(cartProduct.quantity());
            orderProductRepository.save(orderProduct);
        }
    }
}
