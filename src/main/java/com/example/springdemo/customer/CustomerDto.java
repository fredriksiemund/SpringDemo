package com.example.springdemo.customer;

import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class CustomerDto {
    private UUID id;
    private String email;
    private LocalDate birthDate;
}
