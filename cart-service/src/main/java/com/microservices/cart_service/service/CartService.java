package com.microservices.cart_service.service;

import com.microservices.cart_service.entity.Cart;

public interface CartService {

    Cart createCart(Cart cart);

    Cart getCartById(Integer id);
}