package com.example.shorturl.service;

import com.example.shorturl.entity.ShortUrl;

public interface RedirectService {
    ShortUrl findShortUrlByToken(String token) throws Exception;
}
