package com.example.shorturl.dao.impl;

import com.example.shorturl.dao.ShortUrlDao;
import com.example.shorturl.entity.ShortUrl;
import com.example.shorturl.repository.ShortUrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<ShortUrl> findAllByUserId(int userId) {
        return shortUrlRepo.findAllByUserId(userId);
    }

    @Override
    public ShortUrl saveAndFlush(ShortUrl shorturl) {
        return shortUrlRepo.saveAndFlush(shorturl);
    }

    @Override
    public void deleteById(int id) {
        shortUrlRepo.deleteById(id);
    }
}
