package com.example.springdemo.customer;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class CustomerService {
    private final CustomerRepository repository;

    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    public List<Customer> all() {
        return repository.findAll();
    }

    public Customer one(UUID id) {
        return repository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    public Customer create(Customer newCustomer) {
        return repository.save(newCustomer);
    }

    public Customer update(Customer updatedCustomer, UUID id) {
        return repository.findById(id)
                .map(u -> repository.save(updatedCustomer))
                .orElseThrow(NoSuchElementException::new);
    }

    public void delete(UUID id) {
        repository.deleteById(id);
    }
}
