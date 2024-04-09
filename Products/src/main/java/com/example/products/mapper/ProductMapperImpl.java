package com.example.products.mapper;

import com.example.products.dto.ProductDto;
import com.example.products.entity.Product;

public class ProductMapperImpl implements ProductMapper {
    @Override
    public Product toEntity(ProductDto productDto) {
        return Product.builder()
                .id(productDto.id())
                .title(productDto.title())
                .count(productDto.count())
                .price(productDto.price())
                .build();
    }

    public Product toDtoProduct(Product product) {
        return Product.builder()
                .id(product.getId())
                .title(product.getTitle())
                .count(product.getCount())
                .price(product.getPrice())
                .build();
    }
}