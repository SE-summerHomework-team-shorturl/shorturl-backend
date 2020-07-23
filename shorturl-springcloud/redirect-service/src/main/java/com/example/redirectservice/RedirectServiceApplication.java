package com.example.redirectservice;

import com.example.redirectservice.service.RedirectService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@ComponentScan(basePackages = {"com.example.sharedentity","com.example.redirectservice"})
@EntityScan(basePackages = "com.example.sharedentity")
@EnableJpaRepositories(basePackages = "com.example.sharedentity")
@EnableBinding(value={RedirectService.class})
public class RedirectServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(RedirectServiceApplication.class, args);
    }

}
