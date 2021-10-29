package com.example.springdemo.orderItem;

import com.example.springdemo.product.ProductDto;
import lombok.Data;

import java.util.UUID;

@Data
public class OrderItemDto {
    private UUID id;
    private int amount;
    private ProductDto product;
}
