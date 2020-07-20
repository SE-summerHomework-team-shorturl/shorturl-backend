package com.example.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {
    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r ->
                        r.path("/r/**")
                                .uri("lb://redirect-service/"))
                .route(r ->
                        r.path("/user/register")
                                .uri("lb://register-service/"))
                .route(r ->
                        r.path("/login")
                                .filters(f->f
                                        .addRequestParameter("scope","all")
                                        .addRequestParameter("grant_type","password")
                                        .rewritePath("/login","/oauth/token"))
                                .uri("lb://auth-server/"))
                                   .route(r ->
                                   r.path("/urlmanage/addurl")
                                   .uri("lb://addurl-service/"))
                                   .route(r ->
                                   r.path("/urlmanage/findurl")
                                   .uri("lb://userurl-service/"))
                                   .route(r ->
                                   r.path("/urlmanage/deleteurl")
                                   .uri("lb://userurl-service/"))
                                   .build();
                                   }
                                   }
