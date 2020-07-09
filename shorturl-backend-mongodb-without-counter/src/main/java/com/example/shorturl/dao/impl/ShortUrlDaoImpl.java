package com.example.shorturl.dao.impl;

import com.example.shorturl.dao.ShortUrlDao;
import com.example.shorturl.entity.ShortUrl;
import com.example.shorturl.repository.ShortUrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public class ShortUrlDaoImpl implements ShortUrlDao {
    @Autowired private ShortUrlRepository shortUrlRepo;

    @Override
    public ShortUrl findById(String id) {
        return shortUrlRepo.findById(id).orElse(null);
    }

    @Override
    public Page<ShortUrl> findAllByUserId(String userId, Pageable pageable) {
        return shortUrlRepo.findAllByUserId(userId, pageable);
    }

    @Override
    public ShortUrl save(ShortUrl shortUrl) {
        return shortUrlRepo.save(shortUrl);
    }

    @Override
    public void deleteById(String id) {
        shortUrlRepo.deleteById(id);
    }
}
