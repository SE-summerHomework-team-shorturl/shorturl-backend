package com.example.shorturl.manager.dao;

import com.example.shorturl.manager.entity.ShortUrl;
import com.example.shorturl.util.dto.SimplePage;

public interface ShortUrlDao {
    SimplePage<ShortUrl> findAllByUserId(int userId, int page, int size);
    void delete(long shortUrlId, int userId);
}
