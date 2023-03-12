package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.model.Product;

public interface ProductService {
    List<Product> findAllProduct();
    Optional<Product> findByProduct_id(Long id);
    Product save(Product product);
}
