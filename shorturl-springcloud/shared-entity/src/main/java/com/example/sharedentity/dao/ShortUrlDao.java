package com.example.sharedentity.dao;

import com.example.sharedentity.entity.ShortUrl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ShortUrlDao {
    ShortUrl findById(int id);
    List<ShortUrl> findAllByUserId(int userId, Pageable pageable);
    ShortUrl saveAndFlush(ShortUrl shorturl);
    void deleteById(int id);
}
