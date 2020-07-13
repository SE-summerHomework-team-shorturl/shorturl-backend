package com.example.gateway.handler;

import org.springframework.security.web.server.authentication.WebFilterChainServerAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class SuccessHandler extends WebFilterChainServerAuthenticationSuccessHandler {
}
