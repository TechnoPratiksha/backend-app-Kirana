package com.kirana.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
@EnableScheduling
@SpringBootApplication
public class KiranaStoreApplication {
    public static void main(String[] args) {
        SpringApplication.run(KiranaStoreApplication.class, args);
    }
}

