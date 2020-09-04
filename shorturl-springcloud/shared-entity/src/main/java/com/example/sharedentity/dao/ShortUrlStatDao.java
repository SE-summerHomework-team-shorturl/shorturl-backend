package com.example.sharedentity.dao;

import com.example.sharedentity.entity.ShortUrlStat;

public interface ShortUrlStatDao {
    ShortUrlStat findById(long id);
    ShortUrlStat save(ShortUrlStat shortUrlStat);
}
