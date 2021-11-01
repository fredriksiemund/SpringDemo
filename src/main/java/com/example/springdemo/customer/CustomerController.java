package com.example.springdemo.customer;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
public class CustomerController {
    private final CustomerService service;
    private final CustomerMapper mapper;

    public CustomerController(CustomerService service, CustomerMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping("/v1/customers")
    public ResponseEntity<List<CustomerDto>> all() {
        List<CustomerDto> result = service.all().stream().map(mapper::toDto).collect(Collectors.toList());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/v1/customers/{id}")
    public ResponseEntity<CustomerDto> one(@PathVariable UUID id) {
        return new ResponseEntity<>(mapper.toDto(service.one(id)), HttpStatus.OK);
    }
}
