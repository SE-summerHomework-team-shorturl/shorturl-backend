package com.example.shorturl.dao;

import com.example.shorturl.entity.ShortUrl;

import java.util.Set;

public interface ShortUrlDao {
    String findUrlByShortUrlId(long shortUrlId);
    Set<ShortUrl> findAllByUserId(long userId, int page, int size);
    ShortUrl add(ShortUrl shortUrl);
    boolean delete(long shortUrlId, long userId);
}
