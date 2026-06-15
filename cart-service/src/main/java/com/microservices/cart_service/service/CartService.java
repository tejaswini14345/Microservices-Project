package com.microservices.cart_service.service;

import com.microservices.cart_service.dto.CartResponseDTO;
import com.microservices.cart_service.entity.Cart;

public interface CartService {

    CartResponseDTO createCart(Cart cart);

    Cart getCartById(Integer id);
}