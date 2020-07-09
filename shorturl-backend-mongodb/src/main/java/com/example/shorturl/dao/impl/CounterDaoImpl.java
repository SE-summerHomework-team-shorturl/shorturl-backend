package com.example.shorturl.dao.impl;

import com.example.shorturl.dao.CounterDao;
import com.example.shorturl.entity.Counter;
import com.example.shorturl.repository.CounterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

@Repository
public class CounterDaoImpl implements CounterDao {
    @Autowired private CounterRepository counterRepo;
    @Autowired private MongoTemplate mongoTemplate;

    @Override
    public Counter findById(int id) {
        return counterRepo.findById(id).orElse(null);
    }

    @Override
    public Counter save(Counter counter) {
        return counterRepo.save(counter);
    }

    @Override
    public Counter findAndModify(Query query, Update update) {
        FindAndModifyOptions options = new FindAndModifyOptions();
        return mongoTemplate.findAndModify(query, update, options, Counter.class);
    }
}
