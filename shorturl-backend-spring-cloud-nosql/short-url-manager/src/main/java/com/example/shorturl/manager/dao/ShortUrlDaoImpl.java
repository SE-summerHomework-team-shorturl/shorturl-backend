package com.example.shorturl.manager.dao;

import com.example.shorturl.manager.entity.ShortUrl;
import com.example.shorturl.manager.repository.ShortUrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ShortUrlDaoImpl implements ShortUrlDao {
    @Autowired
    private ShortUrlRepository shortUrlRepository;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public Page<ShortUrl> findAllByUserId(long userId, Pageable pageable) {
        return shortUrlRepository.findAll(pageable);
    }

    @Override
    public long deleteByIdAndUserId(long id, long userId) {
        long ret;
        if ((ret = shortUrlRepository.deleteByIdAndUserId(id, userId)) > 0)
            stringRedisTemplate.delete("url:" + id);
        return ret;
    }
}
