package com.example.springdemo.orderItem;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemService {
    private final OrderItemRepository repository;

    public OrderItemService(OrderItemRepository repository) {
        this.repository = repository;
    }

    public List<OrderItem> all() {
        return repository.findAll();
    }

    public OrderItem create(OrderItem newOrderItem) {
        return repository.save(newOrderItem);
    }
}
