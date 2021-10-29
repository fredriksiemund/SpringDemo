package com.example.springdemo.customer;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
public class CustomerController {
    private final CustomerService service;
    private final CustomerMapper mapper = CustomerMapper.INSTANCE;

    public CustomerController(CustomerService service) {
        this.service = service;
    }

    @GetMapping("/v1/customers")
    public List<CustomerDto> all() {
        return service.all().stream().map(mapper::toDto).collect(Collectors.toList());
    }

    @GetMapping("/v1/customers/{id}")
    public CustomerDto one(@PathVariable UUID id) {
        return mapper.toDto(service.one(id));
    }
}
