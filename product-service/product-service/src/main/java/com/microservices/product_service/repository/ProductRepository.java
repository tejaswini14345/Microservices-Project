package com.microservices.product_service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.microservices.product_service.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    // 🔥 Native Query
    @Query(value = "SELECT * FROM products WHERE price > :price", nativeQuery = true)
    List<Product> findProductsAbovePrice(@Param("price") double price);
}