package com.example.springdemo.order;

import com.example.springdemo.orderItem.OrderItemDto;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
public class OrderDto {
    private UUID id;
    private LocalDate ordered;
    private UUID customerId;
    private List<OrderItemDto> orderItems;
}