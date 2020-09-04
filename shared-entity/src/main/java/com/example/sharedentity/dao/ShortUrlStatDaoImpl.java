package com.example.sharedentity.dao;

import com.example.sharedentity.entity.ShortUrlStat;
import com.example.sharedentity.repository.ShortUrlStatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ShortUrlStatDaoImpl implements ShortUrlStatDao {
    @Autowired
    private ShortUrlStatRepository shortUrlStatRepo;

    @Override
    public ShortUrlStat findById(long id) {
        return shortUrlStatRepo.findById(id).orElse(null);
    }

    @Override
    public ShortUrlStat save(ShortUrlStat shortUrlStat) {
        return shortUrlStatRepo.save(shortUrlStat);
    }
}
