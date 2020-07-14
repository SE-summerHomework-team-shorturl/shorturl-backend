package com.example.shorturl.dao.impl;

import com.example.shorturl.dao.ShortUrlDao;
import com.example.shorturl.entity.ShortUrl;
import com.example.shorturl.misc.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Set;

@Repository
public class ShortUrlDaoImpl implements ShortUrlDao {
    @Autowired
    private StringRedisTemplate template;

    @Override
    public String findUrlByShortUrlId(long shortUrlId) {
        ValueOperations<String, String> valueOps = template.opsForValue();

        return valueOps.get("url:" + shortUrlId);
    }

    @Override
    public Set<ShortUrl> findAllByUserId(long userId, int page, int size) {
        ValueOperations<String, String> valueOps = template.opsForValue();
        ZSetOperations<String, String> zSetOps = template.opsForZSet();

        int start = Math.multiplyExact(page, size);
        int stop = Math.addExact(start, size - 1);
        Set<String> shortUrlIdStrings = zSetOps
                .range("user.short.url.ids:" + userId, start, stop);
        assert shortUrlIdStrings != null;

        Set<ShortUrl> shortUrls = new HashSet<>();
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

        return shortUrls;
    }

    @Override
    public ShortUrl add(ShortUrl shortUrl) {
        ValueOperations<String, String> valueOps = template.opsForValue();
        ZSetOperations<String, String> zSetOps = template.opsForZSet();

        Long shortUrlId = valueOps.increment("seq:short.url.id");
        assert shortUrlId != null;
        if (shortUrlId > Constant.SAFE_INT_MAX)
            throw new RuntimeException("Short url id space has run out");
        shortUrl.setId(shortUrlId);

        valueOps.set("url:" + shortUrlId, shortUrl.getUrl());
        zSetOps.add("user.short.url.ids:" + shortUrl.getUserId(),
                shortUrlId.toString(), shortUrlId);

        return shortUrl;
    }

    @Override
    public boolean delete(long id, long userId) {
        if (id <= 0 || id > Constant.SAFE_INT_MAX)
            return true;

        ZSetOperations<String, String> zSetOps = template.opsForZSet();

        Long cnt = zSetOps.remove(
                "user.short.url.ids:" + userId, Long.toString(id));
        assert cnt != null;
        if (cnt == 0)
            return false;
        template.delete("url:" + id);

        return true;
    }
}
