package com.example.redirectservice.dao;


import com.example.redirectservice.repository.ShortUrlRepository;
import com.example.sharedentity.entity.ShortUrl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

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
    public ShortUrl saveAndFlush(ShortUrl shortUrl) {
        return shortUrlRepo.saveAndFlush(shortUrl);
    }

    @Override
    public void deleteById(int id) {
        shortUrlRepo.deleteById(id);
    }
}
