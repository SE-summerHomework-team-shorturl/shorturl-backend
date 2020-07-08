package com.example.shorturl.repository;

import com.example.shorturl.entity.ShortUrl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ShortUrlRepository extends MongoRepository<ShortUrl, Integer> {
    Page<ShortUrl> findAllByUserId(Integer userId, Pageable pageable);
}
