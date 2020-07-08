package com.example.shorturl.dao.impl;

import com.example.shorturl.dao.CounterDao;
import com.example.shorturl.entity.Counter;
import com.example.shorturl.repository.CounterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CounterDaoImpl implements CounterDao {
    @Autowired private CounterRepository counterRepo;

    @Override
    public Counter findById(int id) {
        return counterRepo.findById(id).orElse(null);
    }

    @Override
    public Counter save(Counter counter) {
        return counterRepo.save(counter);
    }
}
