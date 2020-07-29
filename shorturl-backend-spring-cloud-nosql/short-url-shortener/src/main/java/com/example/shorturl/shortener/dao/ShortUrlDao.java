package com.example.shorturl.shortener.dao;

import com.example.shorturl.shortener.entity.ShortUrl;

public interface ShortUrlDao {
    ShortUrl save(ShortUrl shortUrl);
}
