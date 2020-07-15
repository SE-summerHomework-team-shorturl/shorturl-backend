package com.example.securitygateway.config;

import com.example.securitygateway.misc.ShortUrlUserDetails;
import com.example.sharedentity.entity.User;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.gateway.discovery.DiscoveryClientRouteDefinitionLocator;
import org.springframework.cloud.gateway.discovery.DiscoveryLocatorProperties;
import org.springframework.cloud.gateway.route.RouteDefinitionLocator;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Configuration
public class GatewayConfig {
    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r ->
                        r.path("/r/**")
                                .filters( f-> f.rewritePath("/r/(?<remaining>.*)","/r/${remaining}"))
                                .uri("http://localhost:9000/"))
                .route(r ->
                        r.path("/urlmanage/**")
                                .filters( f->
                                        {
                                            User user = ((ShortUrlUserDetails) SecurityContextHolder.getContext()
                                                    .getAuthentication().getPrincipal()).getUser();
                                            Integer id = user.getId();
                                            return f.addRequestParameter("userId",id.toString());
                                        })
                                .uri("http://localhost:9000/"))
                .build();
    }/*
    //注入discoveryClientRouteDefinitionLocator
    @Bean
    public RouteDefinitionLocator discoveryClientRouteDefinitionLocator(DiscoveryClient discoveryClient, DiscoveryLocatorProperties properties) {
        return new DiscoveryClientRouteDefinitionLocator(discoveryClient, properties);
    }*/
}
