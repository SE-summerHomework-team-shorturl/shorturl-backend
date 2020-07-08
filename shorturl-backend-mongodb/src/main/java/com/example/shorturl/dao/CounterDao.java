package com.example.shorturl.dao;

import com.example.shorturl.entity.Counter;

public interface CounterDao {
    Counter findById(int id);
    Counter save(Counter counter);
}
