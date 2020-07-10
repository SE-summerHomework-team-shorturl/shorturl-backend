package com.example.addurlservice.repository;

import com.example.sharedentity.entity.ShortUrl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShortUrlRepository extends JpaRepository<ShortUrl, Integer> {
    Page<ShortUrl> findAllByUserId(Integer userId, Pageable pageable);
}

