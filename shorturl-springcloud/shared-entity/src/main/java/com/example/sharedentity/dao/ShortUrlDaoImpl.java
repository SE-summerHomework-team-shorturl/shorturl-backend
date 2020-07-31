package com.example.sharedentity.dao;



import com.example.sharedentity.entity.ShortUrl;
import com.example.sharedentity.entity.UrlStatistic;
import com.example.sharedentity.repository.ShortUrlRepository;
import com.example.sharedentity.repository.UrlStatisticRepository;
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
    @Autowired private UrlStatisticRepository statisticRepo;

    @Override
    @Cacheable(key="#id")
    public ShortUrl findById(long id) {
        return shortUrlRepo.findById(id).orElse(null);
    }

    @Override
    public List<ShortUrl> findAllByUserId(long userId) {
        return shortUrlRepo.findAllByUserId(userId);
    }

    /**
     * @description save the shortUrl to table and flush
     * @param shortUrl
     * @return shorturl after add into table
     */
    @Override
    public ShortUrl saveAndFlush(ShortUrl shortUrl) {
        return shortUrlRepo.saveAndFlush(shortUrl);
    }

    /**
     * @description the same as saveAndFlush but also add new item into 'UrlStaistics'
     * @param shortUrl
     * @return shorturl after add into table
     */
    @Override
    public ShortUrl addAndFlush(ShortUrl shortUrl) {
        UrlStatistic urlStatistic =new UrlStatistic(shortUrl.getId());
        statisticRepo.saveAndFlush(urlStatistic);
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
    @CacheEvict(key="#id")
    public void deleteById(long id) {
        shortUrlRepo.deleteById(id);
        statisticRepo.deleteById(id);
    }

    @Override
    public List<ShortUrl> findAll() {
        return shortUrlRepo.findAll();
    }
}
