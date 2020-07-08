package com.example.shorturl.service;

import com.example.shorturl.entity.Shorturl;

public interface RedirectService {
    Shorturl findShorturlByToken(String token) throws Exception;
}
