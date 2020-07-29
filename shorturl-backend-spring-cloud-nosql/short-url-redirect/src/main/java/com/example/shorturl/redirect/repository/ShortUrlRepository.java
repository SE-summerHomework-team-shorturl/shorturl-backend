package com.example.shorturl.redirect.repository;

import com.example.shorturl.redirect.entity.ShortUrl;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ShortUrlRepository extends MongoRepository<ShortUrl, Long> {}
