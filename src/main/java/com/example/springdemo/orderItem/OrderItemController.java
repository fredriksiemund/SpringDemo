package com.example.springdemo.orderItem;

import com.example.springdemo.order.OrderDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class OrderItemController {
    private final OrderItemService service;
    private final OrderItemMapper mapper;

    public OrderItemController(OrderItemService service, OrderItemMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping(path = "v1/order-items")
    public ResponseEntity<List<OrderItemDto>> all() {
        List<OrderItemDto> result = service.all()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/v1/order-items")
    public ResponseEntity<OrderItemDto> create(@RequestBody OrderItemDto dto) {
        OrderItem result = service.create(mapper.toJpa(dto));
        return new ResponseEntity<>(mapper.toDto(result), HttpStatus.CREATED);
    }
}
