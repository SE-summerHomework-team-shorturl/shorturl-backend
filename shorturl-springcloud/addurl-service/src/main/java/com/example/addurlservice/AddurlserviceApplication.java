package com.example.addurlservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"com.example.sharedentity","com.example.addurlservice"})
@EntityScan(basePackages = {"com.example.sharedentity","com.example.addurlservice"})
@EnableJpaRepositories(basePackages = {"com.example.sharedentity","com.example.addurlservice"})
public class AddurlserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AddurlserviceApplication.class, args);
    }

}
