package com.example.shorturl.redirect.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

@Repository
public class ShortUrlDaoImpl implements ShortUrlDao {
    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public String findUrlByShortUrlId(long shortUrlId) {
        ValueOperations<String, String> valueOps = redisTemplate.opsForValue();

        return valueOps.get("url:" + shortUrlId);
    }
}
