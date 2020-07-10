package com.example.addurlservice.dao;

import com.example.sharedentity.entity.ShortUrl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ShortUrlDao {
    ShortUrl findById(int id);
    Page<ShortUrl> findAllByUserId(int userId, Pageable pageable);
    ShortUrl saveAndFlush(ShortUrl shorturl);
    void deleteById(int id);
}
