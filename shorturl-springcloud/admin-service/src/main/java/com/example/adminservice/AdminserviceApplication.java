package com.example.adminservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@SpringBootApplication
@EnableDiscoveryClient
@EnableGlobalMethodSecurity(prePostEnabled = true)
@ComponentScan(basePackages = {"com.example.sharedentity","com.example.adminservice"})
@EntityScan(basePackages = {"com.example.sharedentity","com.example.adminservice"})
@EnableJpaRepositories(basePackages = {"com.example.sharedentity","com.example.adminservice"})
@EnableMongoRepositories(basePackages = "com.example.sharedentity")
public class AdminserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdminserviceApplication.class, args);
    }

}
