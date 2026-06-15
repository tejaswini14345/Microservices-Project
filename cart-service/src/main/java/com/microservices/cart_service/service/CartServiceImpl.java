package com.microservices.cart_service.service;

import com.microservices.cart_service.dto.CartItemDTO;
import com.microservices.cart_service.dto.CartResponseDTO;
import com.microservices.cart_service.dto.Product;
import com.microservices.cart_service.entity.Cart;
import com.microservices.cart_service.entity.CartItem;
import com.microservices.cart_service.repository.CartRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final WebClient webClient;
    private final KafkaProducerService kafkaProducerService;
    private final AsyncCartService asyncCartService;

    public CartServiceImpl(CartRepository cartRepository,
                           WebClient webClient,
                           KafkaProducerService kafkaProducerService,
                           AsyncCartService asyncCartService) {
        this.cartRepository = cartRepository;
        this.webClient = webClient;
        this.kafkaProducerService = kafkaProducerService;
        this.asyncCartService = asyncCartService;
    }

    @Override
    public CartResponseDTO createCart(Cart cart) {

        if (cart.getItems() == null || cart.getItems().isEmpty()) {
            throw new RuntimeException("Cart must have at least one item");
        }

        CartItem firstItem = cart.getItems().get(0);

        Product product = webClient
                .get()
                .uri("http://localhost:8081/products/" + firstItem.getProductId())
                .retrieve()
                .bodyToMono(Product.class)
                .block();

        if (product == null) {
            throw new RuntimeException("Product not found");
        }

        firstItem.setProductName(product.getName());
        firstItem.setPrice(product.getPrice());
        firstItem.setCart(cart);

        Cart savedCart = cartRepository.save(cart);

        String kafkaMessage =
                "CartId: " + savedCart.getId()
                        + ", ProductId: " + firstItem.getProductId()
                        + ", Quantity: " + firstItem.getQuantity();

        kafkaProducerService.sendMessage(kafkaMessage);

        asyncCartService.processCartAsync();

        List<CartItemDTO> itemDTOs = savedCart.getItems().stream().map(ci -> {
            CartItemDTO dto = new CartItemDTO();
            dto.setProductId(ci.getProductId());
            dto.setProductName(ci.getProductName());
            dto.setPrice(ci.getPrice());
            dto.setQuantity(ci.getQuantity());
            return dto;
        }).toList();

        CartResponseDTO response = new CartResponseDTO();
        response.setCartId(savedCart.getId());
        response.setUserId(savedCart.getUserId());
        response.setItems(itemDTOs);

        return response;
    }

    @Override
    public Cart getCartById(Integer id) {
        return cartRepository.findById(id).orElse(null);
    }
}