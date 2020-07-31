package com.example.shorturl.shortener.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

@Repository
@SuppressWarnings("all")
public class SequenceDaoImpl implements SequenceDao {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public long addAndGetByName(String name) {
        ValueOperations<String, String> valueOps = stringRedisTemplate.opsForValue();

        return valueOps.increment("sequence:" + name);
    }
}
