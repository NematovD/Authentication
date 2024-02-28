package com.example.basicauthentication.service;

import com.example.basicauthentication.entity.Product;

import java.util.List;

public interface ProductService {
    Product getById(Long id);
    List<Product> getALLProduct();
    Product createProduct(Product product);
    Product updateProduct(Product product);
    void deleteById(Long id);
}
