package com.example.springdemo.product;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class ProductService {
    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public List<Product> all() {
        return repository.findAll();
    }

    public Product one(UUID id) {
        Product p = repository.findById(id).orElseThrow(NoSuchElementException::new);
        System.out.println(p);
        return p;
    }

    public Product create(Product product) {
        return repository.save(product);
    }

    public Product update(Product updatedProduct, UUID id) {
        return repository.findById(id)
                .map(p -> repository.save(updatedProduct))
                .orElseThrow(NoSuchElementException::new);
    }

    public void delete(UUID id) {
        repository.deleteById(id);
    }
}
