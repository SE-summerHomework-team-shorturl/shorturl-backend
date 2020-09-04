package com.example.redirectservice.service;

import com.example.sharedentity.entity.ShortUrl;

public interface RedirectService {
    ShortUrl findShortUrlByToken(String token);
}
