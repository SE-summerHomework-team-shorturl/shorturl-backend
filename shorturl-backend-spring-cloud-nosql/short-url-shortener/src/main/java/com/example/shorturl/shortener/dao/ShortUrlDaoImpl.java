package com.example.shorturl.shortener.dao;

import com.example.shorturl.shortener.entity.ShortUrl;
import com.example.shorturl.shortener.repository.ShortUrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ShortUrlDaoImpl implements ShortUrlDao {
    @Autowired
    private ShortUrlRepository shortUrlRepository;

    @Override
    public ShortUrl save(ShortUrl shortUrl) {
        return shortUrlRepository.save(shortUrl);
    }
}
