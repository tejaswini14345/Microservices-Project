package com.microservices.product_service.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.microservices.product_service.entity.Product;
import com.microservices.product_service.exception.ProductNotFoundException;
import com.microservices.product_service.repository.ProductRepository;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    private ProductServiceImpl productService;

    @BeforeEach
    void setUp() {
        productService = new ProductServiceImpl(productRepository);
    }

    @Test
    void getProductByIdReturnsProductWhenPresent() {
        Product product = new Product(1, "Keyboard", 79.99, 4);
        when(productRepository.findById(1)).thenReturn(Optional.of(product));

        Product result = productService.getProductById(1);

        assertEquals("Keyboard", result.getName());
        verify(productRepository).findById(1);
    }

    @Test
    void getProductByIdThrowsWhenMissing() {
        when(productRepository.findById(99)).thenReturn(Optional.empty());

        assertThrows(ProductNotFoundException.class, () -> productService.getProductById(99));
    }

    @Test
    void updateProductChangesEditableFields() {
        Product existing = new Product(1, "Keyboard", 79.99, 4);
        Product update = new Product(null, "Mechanical Keyboard", 99.99, 6);

        when(productRepository.findById(1)).thenReturn(Optional.of(existing));
        when(productRepository.save(existing)).thenReturn(existing);

        Product result = productService.updateProduct(1, update);

        assertEquals("Mechanical Keyboard", result.getName());
        assertEquals(99.99, result.getPrice());
        assertEquals(6, result.getQuantity());
        verify(productRepository).save(existing);
    }
}
