package com.example.products.controller;

import com.example.products.dto.ProductDto;
import com.example.products.entity.Product;
import com.example.products.service.ProductService;
import com.example.products.service.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductServiceImpl productService;

    @Autowired
    public ProductController(ProductServiceImpl productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> getAllProducts() throws SQLException {
        return productService.getAll();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Product> findById(@PathVariable("id") Integer id) throws SQLException {
        Product product = productService.getById(id);
        if (product != null) {
            return ResponseEntity.ok(product);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Void> addProduct(@RequestBody ProductDto productDto) throws SQLException {
        productService.save(productDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateProduct(@PathVariable("id") Integer id, @RequestBody ProductDto productDto) throws SQLException {
        productService.update(productDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("id") Integer id) throws SQLException {
        productService.delete(id);
        return ResponseEntity.ok().build();
    }
}
