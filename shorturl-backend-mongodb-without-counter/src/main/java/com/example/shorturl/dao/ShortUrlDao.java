package com.example.shorturl.dao;

import com.example.shorturl.entity.ShortUrl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ShortUrlDao {
    ShortUrl findById(String id);
    Page<ShortUrl> findAllByUserId(String userId, Pageable pageable);
    ShortUrl save(ShortUrl shorturl);
    void deleteById(String id);
}
