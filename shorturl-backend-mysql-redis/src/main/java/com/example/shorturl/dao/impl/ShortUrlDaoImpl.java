package com.example.shorturl.dao.impl;

import com.example.shorturl.dao.ShortUrlDao;
import com.example.shorturl.entity.ShortUrl;
import com.example.shorturl.repository.ShortUrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public class ShortUrlDaoImpl implements ShortUrlDao {
    @Autowired private ShortUrlRepository shortUrlRepo;

    @Override
    @Cacheable(cacheNames = "short.url.cache", key = "#id", unless = "#result == null")
    public ShortUrl findById(int id) {
        return shortUrlRepo.findById(id).orElse(null);
    }

    @Override
    public Page<ShortUrl> findAllByUserId(int userId, Pageable pageable) {
        return shortUrlRepo.findAllByUserId(userId, pageable);
    }

    @Override
    public ShortUrl saveAndFlush(ShortUrl shortUrl) {
        return shortUrlRepo.saveAndFlush(shortUrl);
    }

    @Override
    @CacheEvict(cacheNames = "short.url.cache", key = "#id")
    public void deleteById(int id) {
        shortUrlRepo.deleteById(id);
    }
}
