package com.example.products.mapper;

import com.example.products.dto.ProductDto;
import com.example.products.entity.Product;

public interface ProductMapper {
    Product toEntity(ProductDto productDto);

    Product toDtoProduct(Product product);
}