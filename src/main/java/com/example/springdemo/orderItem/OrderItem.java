package com.example.springdemo.orderItem;

import com.example.springdemo.product.Product;
import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@Table(name = "order_items")
public class OrderItem {
    @Id
    @GeneratedValue
    private UUID id;
    private int amount;
    @OneToOne
    private Product product;
}
