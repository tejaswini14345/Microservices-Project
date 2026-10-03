package com.microservices.cart_service.service;

import com.microservices.cart_service.client.ProductClient;
import com.microservices.cart_service.dto.AddCartItemRequest;
import com.microservices.cart_service.dto.ProductSummary;
import com.microservices.cart_service.entity.CartItem;
import com.microservices.cart_service.repository.CartItemRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CartService {

    private final CartItemRepository cartItemRepository;
    private final ProductClient productClient;

    public CartService(CartItemRepository cartItemRepository, ProductClient productClient) {
        this.cartItemRepository = cartItemRepository;
        this.productClient = productClient;
    }

    public List<CartItem> getItems() {
        return cartItemRepository.findAll();
    }

    public CartItem addItem(AddCartItemRequest request) {
        ProductSummary product = productClient.getProduct(request.productId());

        if (product == null) {
            throw new ResponseStatusException(HttpStatus.BAD_GATEWAY, "Product Service returned no product");
        }

        if (product.quantity() != null && request.quantity() > product.quantity()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Requested quantity exceeds available inventory");
        }

        CartItem item = new CartItem(
                null,
                product.id(),
                product.name(),
                product.price(),
                request.quantity());

        return cartItemRepository.save(item);
    }

    public void removeItem(Long id) {
        CartItem item = cartItemRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cart item not found"));
        cartItemRepository.delete(item);
    }

    public void clearCart() {
        cartItemRepository.deleteAll();
    }
}
