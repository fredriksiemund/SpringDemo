package com.example.springdemo.orderItem;

import lombok.Data;

import java.util.UUID;

@Data
public class OrderItemDto {
    private UUID id;
    private UUID productId;
    private int amount;
}
