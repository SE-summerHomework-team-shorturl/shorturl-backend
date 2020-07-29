package com.example.sharedentity.dao;

import com.example.sharedentity.entity.ShortUrl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ShortUrlDao {
    ShortUrl findById(long id);
    List<ShortUrl> findAllByUserId(long userId);
    ShortUrl saveAndFlush(ShortUrl shorturl);
    ShortUrl save(ShortUrl shorturl);
    void deleteById(long id);
    List<ShortUrl> findAll();
}
