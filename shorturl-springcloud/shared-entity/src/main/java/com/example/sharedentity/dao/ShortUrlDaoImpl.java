package com.example.sharedentity.dao;



import com.example.sharedentity.entity.ShortUrl;
import com.example.sharedentity.repository.ShortUrlRepository;
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
    public List<ShortUrl> findAllByUserId(int userId) {
        return shortUrlRepo.findAllByUserId(userId);
    }

    @Override
    public ShortUrl saveAndFlush(ShortUrl shortUrl) {
        return shortUrlRepo.saveAndFlush(shortUrl);
    }

    @Override
    public void deleteById(int id) {
        shortUrlRepo.deleteById(id);
    }

    @Override
    public List<ShortUrl> findAll() {
        return shortUrlRepo.findAll();
    }
}
