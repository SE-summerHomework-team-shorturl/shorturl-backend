package com.example.securitygateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"com.example.sharedentity","com.example.securitygateway"})
@EntityScan(basePackages = {"com.example.sharedentity","com.example.securitygateway"})
@EnableJpaRepositories(basePackages = {"com.example.sharedentity","com.example.securitygateway"})
public class SecuritygatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecuritygatewayApplication.class, args);
    }
}
