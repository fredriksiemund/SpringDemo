package com.example.springdemo.order;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class OrderController {
    private final OrderService service;
    private final OrderMapper mapper = OrderMapper.INSTANCE;

    public OrderController(OrderService service) {
        this.service = service;
    }

    @PostMapping(path = "v1/orders")
    @ResponseStatus(HttpStatus.CREATED)
    public OrderDto create(@Valid @RequestBody OrderDto body) {
        Order request = mapper.toJpa(body);
        Order result = service.create(request);
        return mapper.toDto(result);
    }


}
