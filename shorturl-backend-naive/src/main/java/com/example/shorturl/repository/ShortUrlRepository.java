package com.example.shorturl.repository;

import com.example.shorturl.entity.ShortUrl;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShortUrlRepository extends JpaRepository<ShortUrl, Integer> {
    List<ShortUrl> findAllByUserId(Integer userId);
}
