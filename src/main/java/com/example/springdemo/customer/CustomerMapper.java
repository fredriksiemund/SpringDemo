package com.example.springdemo.customer;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface CustomerMapper {
    CustomerDto toDto(Customer model);

    Customer toJpa(CustomerDto dto);
}
