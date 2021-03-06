package com.example.springdemo.order;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class OrderController {
    private final OrderService service;
    private final OrderMapper mapper;

    public OrderController(OrderService service, OrderMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping(path = "v1/orders")
    public ResponseEntity<List<OrderDto>> all() {
        List<OrderDto> result = service.all()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping(path = "v1/orders")
    public ResponseEntity<OrderDto> create(@RequestBody OrderDto body) {
        Order result = service.create(mapper.toJpa(body));
        return new ResponseEntity<>(mapper.toDto(result), HttpStatus.CREATED);
    }
}
