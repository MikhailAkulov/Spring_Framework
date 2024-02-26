package ru.gb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class DiscoveryService {
    public static void main(String[] args) {
        SpringApplication.run(DiscoveryService.class, args);
    }
}

/**
 * http://localhost:8180/api/book
 * http://localhost:8180/api/book/random
 * http://localhost:8280/api/issue
 * http://localhost:8280/api/issue/refresh
 * http://localhost:8380/api/reader
 * http://localhost:8761/eureka/apps
 */
