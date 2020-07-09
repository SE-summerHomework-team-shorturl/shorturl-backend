package com.example.shorturl.dao;

import com.example.shorturl.entity.Counter;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

public interface CounterDao {
    Counter findById(int id);
    Counter save(Counter counter);
    Counter findAndModify(Query query, Update update);
}
