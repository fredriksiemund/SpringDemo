package com.example.springdemo.order;

import com.example.springdemo.customer.CustomerDto;
import com.example.springdemo.orderItem.OrderItemDto;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
// How do we handle incoming requests with only ids?
public class OrderDto {
    private UUID id;
    private LocalDate ordered;
    private CustomerDto customer;
    private List<OrderItemDto> orderItems;
}