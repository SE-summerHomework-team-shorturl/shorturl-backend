package com.example.shorturl.shortener.dao;

import com.example.shorturl.shortener.entity.ShortUrl;
import com.example.shorturl.util.misc.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Repository;

@Repository
@SuppressWarnings(value = "all")
public class ShortUrlDaoImpl implements ShortUrlDao {
    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public ShortUrl add(ShortUrl shortUrl) {
        ValueOperations<String, String> valueOps = redisTemplate.opsForValue();
        ZSetOperations<String, String> zSetOps = redisTemplate.opsForZSet();

        Long shortUrlId = valueOps.increment("short_url_id_seq");
        if (shortUrlId > Constant.SAFE_INT_MAX)
            throw new RuntimeException("Short url id space has run out");
        shortUrl.setId(shortUrlId);

        valueOps.set("url:" + shortUrlId, shortUrl.getUrl());
        zSetOps.add("user_id_to_short_url_ids:" + shortUrl.getUserId(), shortUrlId.toString(), shortUrlId);

        return shortUrl;
    }
}
