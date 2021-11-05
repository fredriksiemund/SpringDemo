package com.example.springdemo.product;

import lombok.*;

import javax.validation.constraints.*;
import java.util.UUID;

@Data
public class ProductDto {
    private UUID id;
    @NotBlank
    private String name;
    @PositiveOrZero
    private Integer amount = 0;
    @PositiveOrZero
    @NotNull
    private Double price;
}
