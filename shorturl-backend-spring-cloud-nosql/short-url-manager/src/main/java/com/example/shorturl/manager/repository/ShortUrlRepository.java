package com.example.shorturl.manager.repository;

import com.example.shorturl.manager.entity.ShortUrl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ShortUrlRepository extends MongoRepository<ShortUrl, Long> {
    Page<ShortUrl> findAllByUserId(Long userId, Pageable pageable);
    long deleteByIdAndUserId(Long id, Long userId);
}
