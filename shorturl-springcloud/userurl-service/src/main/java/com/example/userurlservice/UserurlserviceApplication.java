package com.example.userurlservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableCaching
@EnableDiscoveryClient
@ComponentScan(basePackages = {"com.example.sharedentity","com.example.userurlservice"})
@EntityScan(basePackages = {"com.example.sharedentity","com.example.userurlservice"})
@EnableJpaRepositories(basePackages = {"com.example.sharedentity","com.example.userurlservice"})
public class UserurlserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserurlserviceApplication.class, args);
    }

}
