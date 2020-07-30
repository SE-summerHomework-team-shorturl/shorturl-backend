package com.example.sharedentity.dao;

import com.example.sharedentity.entity.ShortUrl;
import com.example.sharedentity.repository.ShortUrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@CacheConfig(cacheNames={"shorturls"})
public class ShortUrlDaoImpl implements ShortUrlDao {
    @Autowired private ShortUrlRepository shortUrlRepo;

    @Override
    @Cacheable(key = "#id", unless = "#result == null")
    public ShortUrl findById(long id) {
        return shortUrlRepo.findById(id).orElse(null);
    }

    @Override
    public List<ShortUrl> findAllByUserId(long userId) {
        return shortUrlRepo.findAllByUserId(userId);
    }

    @Override
    public ShortUrl saveAndFlush(ShortUrl shortUrl) {
        return shortUrlRepo.saveAndFlush(shortUrl);
    }

    @Override
    public ShortUrl save(ShortUrl shortUrl) {
        return shortUrlRepo.save(shortUrl);
    }

    @Override
    @CacheEvict(key = "#id")
    public void deleteById(long id) {
        shortUrlRepo.deleteById(id);
    }

    @Override
    public List<ShortUrl> findAll() {
        return shortUrlRepo.findAll();
    }
}
