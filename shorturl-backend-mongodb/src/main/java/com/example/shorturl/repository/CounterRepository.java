package com.example.shorturl.repository;

import com.example.shorturl.entity.Counter;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CounterRepository extends MongoRepository<Counter, Integer> {}
