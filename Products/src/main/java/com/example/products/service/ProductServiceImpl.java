package com.example.products.service;

import com.example.products.dto.ProductDto;
import com.example.products.entity.Product;
import com.example.products.mapper.ProductMapper;
import com.example.products.mapper.ProductMapperImpl;
import com.example.products.repository.ProductRepoImpl;
import com.example.products.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
@Service
@Component
public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepo = new ProductRepoImpl();
    private ProductMapper mapper = new ProductMapperImpl();

    @Override
    public ProductDto save(ProductDto productDto) throws SQLException {
        productRepo.save(mapper.toEntity(productDto));
        return productDto;
    }

    @Override
    public Product getById(Integer id) throws SQLException {
        Product product = productRepo.findById(id);
        return (product != null) ? mapper.toDtoProduct(product) : null;
    }

    @Override
    public List<Product> getAll() throws SQLException {
        List<Product> products = productRepo.findAll();
        List<Product> productResponses = new ArrayList<>();
        for (Product product : products) {
            productResponses.add(mapper.toDtoProduct(product));
        }
        return productResponses;
    }

    @Override
    public ProductDto update(ProductDto productDto) throws SQLException {
        productRepo.update(mapper.toEntity(productDto));
        return productDto;
    }

    @Override
    public void delete(Integer id) throws SQLException {
        productRepo.delete(id);
    }
}
