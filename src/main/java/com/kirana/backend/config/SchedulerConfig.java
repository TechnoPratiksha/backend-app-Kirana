package com.kirana.backend.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.kirana.backend.model.Product;
import com.kirana.backend.service.ProductService;

@Component
public class SchedulerConfig {

    @Autowired
    private ProductService productService;

    // Every day at 8 AM
    @Scheduled(cron = "0 0 8 * * ?")
    public void checkExpiryAlerts() {
        List<Product> expiringSoon = productService.getExpiringProducts(7);
        List<Product> expired = productService.getExpiredProducts();

        if (!expiringSoon.isEmpty()) {
            System.out.println("Products expiring in next 7 days:");
            expiringSoon.forEach(p -> System.out.println(p.getName() + " - Expiry: " + p.getExpiryDate()));
        }

        if (!expired.isEmpty()) {
            System.out.println("Products already expired:");
            expired.forEach(p -> System.out.println(p.getName() + " - Expired on: " + p.getExpiryDate()));
        }
    }
}
