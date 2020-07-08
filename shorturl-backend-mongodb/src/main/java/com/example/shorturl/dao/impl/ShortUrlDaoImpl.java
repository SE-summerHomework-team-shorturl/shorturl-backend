package com.example.shorturl.dao.impl;

import com.example.shorturl.dao.ShortUrlDao;
import com.example.shorturl.entity.ShortUrl;
import com.example.shorturl.repository.ShortUrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ShortUrlDaoImpl implements ShortUrlDao {
    @Autowired private ShortUrlRepository shortUrlRepo;

    @Override
    public ShortUrl findById(int id) {
        return shortUrlRepo.findById(id).orElse(null);
    }

    @Override
    public Page<ShortUrl> findAllByUserId(int userId, Pageable pageable) {
        return shortUrlRepo.findAllByUserId(userId, pageable);
    }

    @Override
    public ShortUrl save(ShortUrl shortUrl) {
        return shortUrlRepo.save(shortUrl);
    }

    @Override
    public void deleteById(int id) {
        shortUrlRepo.deleteById(id);
    }
}
