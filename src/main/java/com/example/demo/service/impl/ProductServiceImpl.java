package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Product;
import com.example.demo.repository.IProductRepository;
import com.example.demo.service.ProductService;


@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    IProductRepository productRepository;

    @Override
    public List<Product> findAllProduct() {
        List<Product> products = productRepository.findAll();
        return products;
    }
   
   
}
