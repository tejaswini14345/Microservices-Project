package com.microservices.cart_service.service;

import com.microservices.cart_service.dto.Product;
import com.microservices.cart_service.entity.Cart;
import com.microservices.cart_service.entity.CartItem;
import com.microservices.cart_service.repository.CartRepository;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final RestTemplate restTemplate;

    public CartServiceImpl(CartRepository cartRepository, RestTemplate restTemplate) {
        this.cartRepository = cartRepository;
        this.restTemplate = restTemplate;
    }

    @Override
    public Cart createCart(Cart cart) {

        // safety check: cart must have items
        if (cart.getItems() == null || cart.getItems().isEmpty()) {
            throw new RuntimeException("Cart must have at least one item");
        }

        // take first item
        CartItem item = cart.getItems().get(0);

        // call product service
        String url = "http://localhost:8081/products/" + item.getProductId();

        Product product = restTemplate.getForObject(url, Product.class);

        // null check FIRST
        if (product == null) {
            throw new RuntimeException("Product not found");
        }

        // fill product details into cart item
        item.setProductName(product.getName());
        item.setPrice(product.getPrice());

        // link item to cart (VERY IMPORTANT)
        item.setCart(cart);

        return cartRepository.save(cart);
    }

    @Override
    public Cart getCartById(Integer id) {
        return cartRepository.findById(id).orElse(null);
    }
}