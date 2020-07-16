package com.example.urlshortener.shortener.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @GetMapping(value = "/test/normal")
    public Object normal() {
        return SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping(value = "/test/admin-required")
    public Object adminRequired() {
        return SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
