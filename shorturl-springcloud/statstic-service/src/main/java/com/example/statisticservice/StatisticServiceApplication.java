package com.example.statisticservice;

import com.example.statisticservice.service.IReceiverService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableEurekaClient
@EnableBinding(value={IReceiverService.class})
@ComponentScan(basePackages = {"com.example.sharedentity","com.example.statisticservice"})
@EntityScan(basePackages = "com.example.sharedentity")
@EnableJpaRepositories(basePackages = "com.example.sharedentity")
public class StatisticServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(StatisticServiceApplication.class, args);
    }

}
