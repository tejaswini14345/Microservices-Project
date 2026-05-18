package com.microservices.cart_service.service;

import com.microservices.cart_service.dto.Product;
import com.microservices.cart_service.entity.Cart;
import com.microservices.cart_service.entity.CartItem;
import com.microservices.cart_service.repository.CartRepository;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final WebClient webClient;

    // constructor injection
    public CartServiceImpl(CartRepository cartRepository, WebClient webClient) {
        this.cartRepository = cartRepository;
        this.webClient = webClient;
    }

    @Override
    public Cart createCart(Cart cart) {

        // validate cart
        if (cart.getItems() == null || cart.getItems().isEmpty()) {
            throw new RuntimeException("Cart must have at least one item");
        }

        CartItem item = cart.getItems().get(0);

        // call product service
        String url = "http://localhost:8081/products/" + item.getProductId();
        Product product = webClient
        .get()
        .uri(url)
        .retrieve()
        .bodyToMono(Product.class)
        .block();

        if (product == null) {
            throw new RuntimeException("Product not found");
        }

        // fill data
        item.setProductName(product.getName());
        item.setPrice(product.getPrice());

        // link cart
        item.setCart(cart);

        return cartRepository.save(cart);
    }

    @Override
    public Cart getCartById(Integer id) {
        return cartRepository.findById(id).orElse(null);
    }
}