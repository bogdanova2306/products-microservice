package com.example.products.repository;

import com.example.products.entity.Product;

import java.sql.SQLException;
import java.util.List;

public interface ProductRepository {
    void save(Product product) throws SQLException;
    Product findById(Integer id) throws SQLException;
    List<Product> findAll() throws SQLException;
    void update(Product product) throws SQLException;
    void delete(Integer id) throws SQLException;
}
