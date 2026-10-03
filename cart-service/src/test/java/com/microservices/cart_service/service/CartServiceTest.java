package com.microservices.cart_service.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import com.microservices.cart_service.client.ProductClient;
import com.microservices.cart_service.dto.AddCartItemRequest;
import com.microservices.cart_service.dto.ProductSummary;
import com.microservices.cart_service.entity.CartItem;
import com.microservices.cart_service.repository.CartItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

@ExtendWith(MockitoExtension.class)
class CartServiceTest {

    @Mock
    private CartItemRepository cartItemRepository;

    @Mock
    private ProductClient productClient;

    private CartService cartService;

    @BeforeEach
    void setUp() {
        cartService = new CartService(cartItemRepository, productClient);
    }

    @Test
    void addItemEnrichesProductInformation() {
        ProductSummary product = new ProductSummary(10, "Keyboard", 79.99, 5);
        AddCartItemRequest request = new AddCartItemRequest(10, 2);

        when(productClient.getProduct(10)).thenReturn(product);
        when(cartItemRepository.save(org.mockito.ArgumentMatchers.any(CartItem.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        CartItem result = cartService.addItem(request);

        assertEquals(10, result.getProductId());
        assertEquals("Keyboard", result.getProductName());
        assertEquals(2, result.getQuantity());
    }

    @Test
    void addItemRejectsQuantityAboveInventory() {
        ProductSummary product = new ProductSummary(10, "Keyboard", 79.99, 1);
        when(productClient.getProduct(10)).thenReturn(product);

        assertThrows(
                ResponseStatusException.class,
                () -> cartService.addItem(new AddCartItemRequest(10, 2)));
    }

    @Test
    void removeItemDeletesExistingItem() {
        CartItem item = new CartItem(4L, 10, "Keyboard", 79.99, 1);
        when(cartItemRepository.findById(4L)).thenReturn(Optional.of(item));

        cartService.removeItem(4L);

        verify(cartItemRepository).delete(item);
    }
}
