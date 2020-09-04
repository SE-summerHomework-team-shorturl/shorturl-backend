package com.example.sharedentity.repository;

import com.example.sharedentity.entity.ShortUrlStat;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ShortUrlStatRepository extends MongoRepository<ShortUrlStat, Long> {}
