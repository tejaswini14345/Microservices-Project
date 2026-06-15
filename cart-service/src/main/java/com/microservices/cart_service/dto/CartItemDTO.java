package com.microservices.cart_service.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartItemDTO {

    private Integer productId;
    private String productName;
    private Double price;
    private Integer quantity;
}