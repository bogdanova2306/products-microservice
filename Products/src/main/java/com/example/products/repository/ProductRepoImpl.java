package com.example.products.repository;

import com.example.products.entity.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.products.utils.Util.*;

public class ProductRepoImpl implements ProductRepository {

    @Override
    public void save(Product product) throws SQLException {
        String sql = "INSERT INTO products (id, title, count, price) VALUES (?, ?, ?, ?)";
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, String.valueOf(product.getId()));
            statement.setString(2, product.getTitle());
            statement.setString(3, String.valueOf(product.getCount()));
            statement.setString(4, String.valueOf(product.getPrice()));

            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Не удалось создать продукт");
            }
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        }
    }

    @Override
    public Product findById(Integer id) throws SQLException {
        Product product = null;
        String sql = "SELECT id, title, count, price FROM products WHERE id = ?";
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                if (product == null) {
                    product = mapProduct(resultSet);
                }
            }
            if (product == null) {
                throw new SQLException("Не удалось найти продукт с id " + id);
            }
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        }
        return product;
    }

    @Override
    public List<Product> findAll() throws SQLException {
        List<Product> products = new ArrayList<>();

        String sql = "SELECT * FROM products";
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();

            Map<Integer, Product> productMap = new HashMap<>();
            while (resultSet.next()) {
                Integer productId = resultSet.getInt("id");
                Product product = productMap.get(productId);
                if (product == null) {
                    product = mapProduct(resultSet);
                    products.add(product);
                    productMap.put(productId, product);
                }
            }
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        }
        return products;
    }

    @Override
    public void update(Product product) throws SQLException {
        String sql = "UPDATE products SET title = ?, count = ?, price = ? WHERE id = ?";
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, product.getTitle());
            statement.setString(2, String.valueOf(product.getCount()));
            statement.setString(3, String.valueOf(product.getPrice()));
            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Продукта с id " + product.getId() + " не существует");
            }
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        }
    }

    @Override
    public void delete(Integer id) throws SQLException {
        String sql = "DELETE FROM products WHERE id = ?";
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Продукта с id " + id + " не существует");
            }
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        }
    }

    private Product mapProduct(ResultSet resultSet) throws SQLException {
        return Product.builder()
                .id(resultSet.getInt("id"))
                .title(resultSet.getString("title"))
                .count(Integer.valueOf(resultSet.getString("count")))
                .price(Integer.valueOf(resultSet.getString("price")))
                .build();
    }
}
