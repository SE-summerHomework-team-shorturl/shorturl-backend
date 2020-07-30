package com.example.redirectservice;

import com.example.redirectservice.service.ISendeService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableCaching
@EnableDiscoveryClient
@ComponentScan(basePackages = {"com.example.sharedentity", "com.example.redirectservice"})
@EntityScan(basePackages = "com.example.sharedentity")
@EnableJpaRepositories(basePackages = "com.example.sharedentity")
@EnableBinding(value={ISendeService.class})
public class RedirectserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(RedirectserviceApplication.class, args);
    }

}
