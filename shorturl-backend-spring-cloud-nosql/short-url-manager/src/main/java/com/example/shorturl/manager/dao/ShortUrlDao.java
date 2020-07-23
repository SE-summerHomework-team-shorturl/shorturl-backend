package com.example.shorturl.manager.dao;

import com.example.shorturl.manager.entity.ShortUrl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ShortUrlDao {
    Page<ShortUrl> findAllByUserId(long userId, Pageable pageable);
    long deleteByIdAndUserId(long id, long userId);
}
