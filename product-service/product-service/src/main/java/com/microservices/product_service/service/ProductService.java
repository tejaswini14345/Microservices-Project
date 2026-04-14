package com.microservices.product_service.service;

import java.util.List;

import com.microservices.product_service.entity.Product;

public interface ProductService {

    Product createProduct(Product product);

    Product getProductById(Integer id);

    List<Product> getAllProducts();
}