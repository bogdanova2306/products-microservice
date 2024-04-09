package com.example.products.entity;

import lombok.*;

import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Product {
    private Integer id;
    private String title;
    private Integer count;
    private Integer price;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return getId().equals(product.getId()) && getTitle().equals(product.getTitle()) &&
                Objects.equals(getCount(), product.getCount()) && Objects.equals(getPrice(), product.getPrice());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTitle(), getCount(), getPrice());
    }

    @Override
    public String toString(){
        return String.valueOf(id);
    }
}

