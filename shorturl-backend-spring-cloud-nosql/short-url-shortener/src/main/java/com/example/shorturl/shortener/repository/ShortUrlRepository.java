package com.example.shorturl.shortener.repository;

import com.example.shorturl.shortener.entity.ShortUrl;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ShortUrlRepository extends MongoRepository<ShortUrl, Long> {}
