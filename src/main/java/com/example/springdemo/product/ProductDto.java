package com.example.springdemo.product;

import lombok.Data;

import java.util.UUID;

@Data
public class ProductDto {
    private UUID id;
    private String name;
    private int amount = 0;
    private double price;
}
