package com.microservices.product_service.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.microservices.product_service.entity.Product;
import com.microservices.product_service.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product getProductById(Integer id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public void deleteProduct(Integer id) {
        productRepository.deleteById(id);
    }

    @Override
public List<Product> getProductsAbovePrice(double price) {
    return productRepository.findProductsAbovePrice(price);
}

    @Override
    public Product updateProduct(Integer id, Product product) {
        Product existing = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        existing.setName(product.getName());
        existing.setPrice(product.getPrice());
        existing.setQuantity(product.getQuantity());

        return productRepository.save(existing);
    }

    // 🔥 PAGINATION + SORTING
    @Override
    public List<Product> getProductsPaginated(int page, int size, String sortBy) {

        Page<Product> productPage = productRepository.findAll(
                PageRequest.of(page, size, Sort.by(sortBy))
        );

        return productPage.getContent();
    }

    // 🔥 STREAMS FILTER
    @Override
    public List<Product> filterExpensiveProducts(double minPrice) {

        return productRepository.findAll()
                .stream()
                .filter(p -> p.getPrice() > minPrice)
                .toList();
    }
}