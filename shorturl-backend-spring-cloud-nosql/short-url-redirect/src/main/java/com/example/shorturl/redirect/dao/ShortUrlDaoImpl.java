package com.example.shorturl.redirect.dao;

import com.example.shorturl.redirect.entity.ShortUrl;
import com.example.shorturl.redirect.repository.ShortUrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

@Repository
public class ShortUrlDaoImpl implements ShortUrlDao {
    @Autowired
    private ShortUrlRepository shortUrlRepository;
    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public String findUrlByShortUrlId(long shortUrlId) {
        ValueOperations<String, String> valueOps = redisTemplate.opsForValue();

        String url = valueOps.get("url:" + shortUrlId);

        if (url == null) {
            ShortUrl shortUrl = shortUrlRepository.findById(shortUrlId).orElse(null);
            if (shortUrl != null) {
                url = shortUrl.getUrl();
                valueOps.set("url:" + shortUrlId, url);
            }
        }

        return url;
    }
}
