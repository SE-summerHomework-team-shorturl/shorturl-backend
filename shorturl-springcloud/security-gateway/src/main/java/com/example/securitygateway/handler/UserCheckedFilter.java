package com.example.securitygateway.handler;

import com.example.securitygateway.misc.ShortUrlUserDetails;
import com.example.sharedentity.entity.User;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class UserCheckedFilter implements Ordered, GlobalFilter {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        if(request.getHeaders().get("Authorization") != null) {
            User user = ((ShortUrlUserDetails) SecurityContextHolder.getContext()
                    .getAuthentication().getPrincipal()).getUser();

            request.getQueryParams().add("userId", user.getId().toString());
            exchange.mutate().request(request).build();
        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }
}
