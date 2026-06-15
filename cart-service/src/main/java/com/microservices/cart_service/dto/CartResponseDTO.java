package com.microservices.cart_service.dto;

import lombok.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartResponseDTO {

    private Integer cartId;
    private Integer userId;
    private List<CartItemDTO> items;
}