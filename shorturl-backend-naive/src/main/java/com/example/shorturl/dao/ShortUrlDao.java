package com.example.shorturl.dao;

import com.example.shorturl.entity.ShortUrl;

import java.util.List;

public interface ShortUrlDao {
    ShortUrl findById(int id);
    List<ShortUrl> findAllByUserId(int userId);
    ShortUrl saveAndFlush(ShortUrl shorturl);
    void deleteById(int id);
}
