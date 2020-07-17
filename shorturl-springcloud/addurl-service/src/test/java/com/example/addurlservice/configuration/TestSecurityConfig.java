package com.example.addurlservice.configuration;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class TestSecurityConfig {
    @Bean
    public MyUserDetailsService userDetailsService() {
        return new MyUserDetailsService();
    }
}