package com.example.springdemo.product;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
public class ProductController {
    private final ProductService service;
    private final ProductMapper mapper = ProductMapper.INSTANCE;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping(path = "v1/products")
    public List<ProductDto> all() {
        return service.all()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("v1/products/{id}")
    public ProductDto one(@PathVariable UUID id) {
        ProductDto d = mapper.toDto(service.one(id));
        System.out.println(d);
        return d;
    }

    @PostMapping(path = "v1/products")
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDto create(@Valid @RequestBody ProductDto body) {
        Product request = mapper.toJpa(body);
        Product result = service.create(request);
        return mapper.toDto(result);
    }

    @PatchMapping("v1/products/{id}")
    public ProductDto update(@RequestBody ProductDto body, @PathVariable UUID id) {
        Product request = mapper.toJpa(body);
        Product result = service.update(request, id);
        return mapper.toDto(result);
    }

    @DeleteMapping("v1/products/{id}")
    public void delete(@PathVariable UUID id) {
        service.delete(id);
    }
}
