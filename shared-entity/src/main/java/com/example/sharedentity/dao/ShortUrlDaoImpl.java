package com.example.sharedentity.dao;


import com.example.sharedentity.entity.ShortUrl;
import com.example.sharedentity.entity.ShortUrlStat;
import com.example.sharedentity.repository.ShortUrlRepository;
import com.example.sharedentity.repository.ShortUrlStatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@CacheConfig(cacheNames = {"shorturls"})
public class ShortUrlDaoImpl implements ShortUrlDao {
    @Autowired
    private ShortUrlRepository shortUrlRepo;
    @Autowired
    private ShortUrlStatRepository shortUrlStatRepo;

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
    public List<ShortUrl> findAllByUserIdFetchStat(long userId) {
        List<ShortUrl> shortUrls = shortUrlRepo.findAllByUserId(userId);
        for (ShortUrl shortUrl : shortUrls)
            fetchStat(shortUrl);
        return shortUrls;
    }

    @Override
    public ShortUrl saveAndFlush(ShortUrl shortUrl) {
        return shortUrlRepo.saveAndFlush(shortUrl);
    }

    @Override
    public ShortUrl save(ShortUrl shorturl) {
        return shortUrlRepo.save(shorturl);
    }

    @Override
    public List<ShortUrl> saveAll(List<ShortUrl> shortUrls) {
        return shortUrlRepo.saveAll(shortUrls);
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

    @Override
    public List<ShortUrl> findAllFetchStat() {
        List<ShortUrl> shortUrls = shortUrlRepo.findAll();
        for (ShortUrl shortUrl : shortUrls)
            fetchStat(shortUrl);
        return shortUrls;
    }

    private void fetchStat(ShortUrl shortUrl) {
        long shortUrlId = shortUrl.getId();
        ShortUrlStat shortUrlStat = shortUrlStatRepo.findById(shortUrlId).orElse(null);
        if (shortUrlStat == null)
            shortUrlStat = new ShortUrlStat(shortUrlId);
        shortUrl.setShortUrlStat(shortUrlStat);
    }
}
