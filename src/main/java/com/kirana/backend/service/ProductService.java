package com.kirana.backend.service;

import com.kirana.backend.model.Product;
import com.kirana.backend.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // Products expiring in next 'days'
    public List<Product> getExpiringProducts(int days) {
        LocalDate targetDate = LocalDate.now().plusDays(days);
        return productRepository.findByExpiryDateBefore(targetDate);
    }

    // Products already expired
    public List<Product> getExpiredProducts() {
        LocalDate today = LocalDate.now();
        return productRepository.findByExpiryDateBefore(today);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
