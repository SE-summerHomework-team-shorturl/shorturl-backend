package com.example.userservice.service;


import com.example.sharedentity.entity.Shorturl;

public interface RedirectService {
    Shorturl findShorturlByToken(String token) throws Exception;
}
