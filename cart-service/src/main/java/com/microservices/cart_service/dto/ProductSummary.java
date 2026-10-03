package com.microservices.cart_service.dto;

public record ProductSummary(
        Integer id,
        String name,
        Double price,
        Integer quantity) {
}
