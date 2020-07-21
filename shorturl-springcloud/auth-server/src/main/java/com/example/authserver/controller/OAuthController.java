package com.example.authserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OAuthController {
    @Autowired
    private ConsumerTokenServices consumerTokenServices;

    @GetMapping(value = "/oauth/revoke_token")
    public boolean revokeToken(@RequestParam(value = "token") String token) {
        return consumerTokenServices.revokeToken(token);
    }
}
