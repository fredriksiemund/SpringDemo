package com.example.springdemo.orderItem;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface OrderItemMapper {
    @Mapping(target = "productId", source = "product.id")
    OrderItemDto toDto(OrderItem source);

    @Mapping(target = "product.id", source = "productId")
    OrderItem toJpa(OrderItemDto source);
}
