package com.microservices.product_service.service;

import java.util.List;

import com.microservices.product_service.entity.Product;

public interface ProductService {

    Product createProduct(Product product);
    List<Product> getAllProducts();
    Product getProductById(Integer id);
    Product updateProduct(Integer id, Product product);
    void deleteProduct(Integer id);
}