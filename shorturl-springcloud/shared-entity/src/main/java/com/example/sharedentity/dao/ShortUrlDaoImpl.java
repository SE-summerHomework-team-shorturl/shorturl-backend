package com.example.sharedentity.dao;



import com.example.sharedentity.entity.ShortUrl;
import com.example.sharedentity.repository.ShortUrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@CacheConfig(cacheNames={"shorturls"})
public class ShortUrlDaoImpl implements ShortUrlDao {
    @Autowired private ShortUrlRepository shortUrlRepo;

    @Override
    @Cacheable(key="#id")
    public ShortUrl findById(int id) {
        return shortUrlRepo.findById(id).orElse(null);
    }

    @Override
    public List<ShortUrl> findAllByUserId(int userId) {
        return shortUrlRepo.findAllByUserId(userId);
    }

    @Override
    @CachePut(key="#shortUrl.getId()")
    public ShortUrl saveAndFlush(ShortUrl shortUrl) {
        return shortUrlRepo.saveAndFlush(shortUrl);
    }

    @Override
    @CachePut(key="#shortUrl.getId()")
    public ShortUrl save(ShortUrl shortUrl) {
        return shortUrlRepo.save(shortUrl);
    }

    @Override
    @CacheEvict(key="#id")
    public void deleteById(int id) {
        shortUrlRepo.deleteById(id);
    }

    @Override
    public List<ShortUrl> findAll() {
        return shortUrlRepo.findAll();
    }
}
