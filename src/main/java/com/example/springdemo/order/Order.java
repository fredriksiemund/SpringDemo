package com.example.springdemo.order;

import com.example.springdemo.customer.Customer;
import com.example.springdemo.orderItem.OrderItem;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue
    private UUID id;
    private LocalDate ordered;
    @OneToOne
    private Customer customer;
    @ManyToMany
    private List<OrderItem> orderItems;
}
