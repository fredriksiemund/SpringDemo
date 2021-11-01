package com.example.springdemo.product;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
public class ProductController {
    private final ProductService service;
    private final ProductMapper mapper;

    public ProductController(ProductService service, ProductMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping(path = "v1/products")
    public ResponseEntity<List<ProductDto>> all() {
        List<ProductDto> result = service.all()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("v1/products/{id}")
    public ResponseEntity<ProductDto> one(@PathVariable UUID id) {
        ProductDto result = mapper.toDto(service.one(id));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping(path = "v1/products")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ProductDto> create(@Valid @RequestBody ProductDto body) {
        Product result = service.create(mapper.toJpa(body));
        return new ResponseEntity<>(mapper.toDto(result), HttpStatus.OK);
    }

    @PatchMapping("v1/products/{id}")
    public ResponseEntity<ProductDto> update(@RequestBody ProductDto body, @PathVariable UUID id) {
        Product result = service.update(mapper.toJpa(body), id);
        return new ResponseEntity<>(mapper.toDto(result), HttpStatus.OK);
    }

    @DeleteMapping("v1/products/{id}")
    public void delete(@PathVariable UUID id) {
        service.delete(id);
    }
}
