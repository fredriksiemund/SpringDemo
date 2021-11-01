package com.example.springdemo.order;

import com.example.springdemo.orderItem.OrderItemMapper;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", // To create a bean for autowiring
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        uses = OrderItemMapper.class)
public interface OrderMapper {

    @Mapping(target = "customerId", source = "customer.id")
    @Mapping(target = "orderItems", source = "orderItems")
    OrderDto toDto(Order source);

    @Mapping(target = "customer.id", source = "customerId")
    @Mapping(target = "orderItems", source = "orderItems")
    Order toJpa(OrderDto source);

}
