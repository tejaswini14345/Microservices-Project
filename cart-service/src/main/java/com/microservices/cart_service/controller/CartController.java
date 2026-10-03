package com.microservices.cart_service.controller;

import com.microservices.cart_service.dto.AddCartItemRequest;
import com.microservices.cart_service.entity.CartItem;
import com.microservices.cart_service.service.CartService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/cart/items")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping
    public ResponseEntity<List<CartItem>> getItems() {
        return ResponseEntity.ok(cartService.getItems());
    }

    @PostMapping
    public ResponseEntity<CartItem> addItem(@Valid @RequestBody AddCartItemRequest request) {
        CartItem created = cartService.addItem(request);
        return ResponseEntity
                .created(URI.create("/cart/items/" + created.getId()))
                .body(created);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeItem(@PathVariable Long id) {
        cartService.removeItem(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> clearCart() {
        cartService.clearCart();
        return ResponseEntity.noContent().build();
    }
}
