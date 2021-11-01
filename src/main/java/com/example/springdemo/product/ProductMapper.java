package com.example.springdemo.product;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ProductMapper {
    ProductDto toDto(Product product);

    Product toJpa(ProductDto productDto);
}
