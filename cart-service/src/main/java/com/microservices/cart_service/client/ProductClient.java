package com.microservices.cart_service.client;

import com.microservices.cart_service.dto.ProductSummary;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class ProductClient {

    private final RestClient restClient;

    public ProductClient(@Value("${services.product.base-url}") String productBaseUrl) {
        this.restClient = RestClient.builder()
                .baseUrl(productBaseUrl)
                .build();
    }

    public ProductSummary getProduct(Integer productId) {
        return restClient.get()
                .uri("/products/{id}", productId)
                .retrieve()
                .body(ProductSummary.class);
    }
}
