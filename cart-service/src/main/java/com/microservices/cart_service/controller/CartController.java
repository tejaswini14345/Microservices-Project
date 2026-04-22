package com.microservices.cart_service.controller;

import com.microservices.cart_service.entity.Cart;
import com.microservices.cart_service.service.CartService;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/carts")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping
    public Cart createCart(@RequestBody Cart cart) {
        return cartService.createCart(cart);
    }

    @GetMapping("/{id}")
    public Cart getCart(@PathVariable Integer id) {
        return cartService.getCartById(id);
    }
}