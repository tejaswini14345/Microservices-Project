package com.microservices.product_service.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.product_service.entity.Product;
import com.microservices.product_service.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // CREATE
    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return productService.createProduct(product);
    }

    // READ ALL
    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    // READ BY ID
    @GetMapping("/{id}")
    public Product getProduct(@PathVariable Integer id) {
        return productService.getProductById(id);
    }

    // UPDATE
    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Integer id,
                                 @RequestBody Product product) {
        return productService.updateProduct(id, product);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable Integer id) {
        productService.deleteProduct(id);
        return "Product deleted successfully";
    }

    // 🔥 PAGINATION API
    @GetMapping("/page")
    public List<Product> getProductsPaginated(
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam String sortBy
    ) {
        return productService.getProductsPaginated(page, size, sortBy);
    }

    // 🔥 STREAM FILTER API
    @GetMapping("/filter")
    public List<Product> filterProducts(@RequestParam double minPrice) {
        return productService.filterExpensiveProducts(minPrice);
    }
}