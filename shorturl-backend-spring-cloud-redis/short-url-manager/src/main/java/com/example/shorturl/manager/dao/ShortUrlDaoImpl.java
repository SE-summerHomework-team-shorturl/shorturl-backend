package com.example.shorturl.manager.dao;

import com.example.shorturl.manager.entity.ShortUrl;
import com.example.shorturl.util.dto.SimplePage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Repository
@SuppressWarnings("all")
public class ShortUrlDaoImpl implements ShortUrlDao {
    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public SimplePage<ShortUrl> findAllByUserId(int userId, int page, int size) {
        ValueOperations<String, String> valueOps = redisTemplate.opsForValue();
        ZSetOperations<String, String> zSetOps = redisTemplate.opsForZSet();

        int start = Math.multiplyExact(page, size);
        int stop = Math.addExact(start, size - 1);
        Set<String> shortUrlIdStrings = zSetOps.range("user_id_to_short_url_ids:" + userId, start, stop);

        List<ShortUrl> shortUrls = new ArrayList<>();
        for (String shortUrlIdString : shortUrlIdStrings) {
            long shortUrlId = Long.parseLong(shortUrlIdString);
            String url = valueOps.get("url:" + shortUrlId);
            if (url != null) {
                ShortUrl shortUrl = new ShortUrl();
                shortUrl.setId(shortUrlId);
                shortUrl.setUrl(url);
                shortUrl.setUserId(userId);
                shortUrls.add(shortUrl);
            }
        }

        long totalElements = zSetOps.zCard("user_id_to_short_url_ids:" + userId);

        return new SimplePage<>(page, size, totalElements, shortUrls);
    }

    @Override
    public void delete(long shortUrlId, int userId) {
        ZSetOperations<String, String> zSetOps = redisTemplate.opsForZSet();

        long cnt = zSetOps.remove("user_id_to_short_url_ids:" + userId, Long.toString(shortUrlId));
        if (cnt != 0)
            redisTemplate.delete("url:" + shortUrlId);
    }
}
