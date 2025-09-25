package com.kirana.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kirana.backend.model.Product;
import com.kirana.backend.service.ProductService;

@CrossOrigin(origins = "http://localhost:3000") 


@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    // Single product
    @PostMapping
    public Product addProduct(@RequestBody Product product) {
        return productService.addProduct(product);
    }

    // Multiple products (batch)
    @PostMapping("/batch")
    public List<Product> addProducts(@RequestBody List<Product> products) {
        return products.stream()
                       .map(productService::addProduct)
                       .toList();
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/expiring")
    public List<Product> getExpiringProducts(@RequestParam int days) {
        return productService.getExpiringProducts(days);
    }

    @GetMapping("/expired")
    public List<Product> getExpiredProducts() {
        return productService.getExpiredProducts();
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return "Product deleted successfully";
    }
}
