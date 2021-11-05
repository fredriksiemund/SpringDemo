package com.example.springdemo.product;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Data
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue
    private UUID id;
    private String name;
    private Integer amount;
    private Double price;
}
