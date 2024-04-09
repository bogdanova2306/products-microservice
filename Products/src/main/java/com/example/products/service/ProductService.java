package com.example.products.service;

import com.example.products.dto.ProductDto;
import com.example.products.entity.Product;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.List;

public interface ProductService {

    ProductDto save(ProductDto productDto) throws SQLException;

    Product getById(Integer id) throws SQLException;

    List<Product> getAll() throws SQLException;

    ProductDto update(ProductDto productDto) throws SQLException;

    void delete(Integer id) throws SQLException;
}
