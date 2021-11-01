package com.example.springdemo.order;

import com.example.springdemo.orderItem.OrderItem;
import com.example.springdemo.orderItem.OrderItemRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class OrderService {
    private final OrderRepository repository;
    private final OrderItemRepository orderItemRepository;

    public OrderService(OrderRepository repository, OrderItemRepository orderItemRepository) {
        this.repository = repository;
        this.orderItemRepository = orderItemRepository;
    }

    public List<Order> all() {
        return repository.findAll();
    }

    public Order one(UUID id) {
        return repository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Transactional
    public Order create(Order newOrder) {
        orderItemRepository.saveAll(newOrder.getOrderItems());
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
