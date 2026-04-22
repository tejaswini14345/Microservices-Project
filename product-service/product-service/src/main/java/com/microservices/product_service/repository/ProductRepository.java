package com.microservices.product_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservices.product_service.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}