package com.example.springdemo.order;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class OrderService {
    private final OrderRepository repository;

    public OrderService(OrderRepository repository) {
        this.repository = repository;
    }

    public List<Order> all() {
        return repository.findAll();
    }

    public Order one(UUID id) {
        return repository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    public Order create(Order newOrder) {
        return repository.save(newOrder);
    }

    public Order update(Order updatedOrder, UUID id) {
        return repository.findById(id)
                .map(u -> repository.save(updatedOrder))
                .orElseThrow(NoSuchElementException::new);
    }

    public void delete(UUID id) {
        repository.deleteById(id);
    }
}
