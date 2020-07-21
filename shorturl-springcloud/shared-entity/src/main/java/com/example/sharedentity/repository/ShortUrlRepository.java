package com.example.sharedentity.repository;

import com.example.sharedentity.entity.ShortUrl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShortUrlRepository extends JpaRepository<ShortUrl, Integer> {
    List<ShortUrl> findAllByUserId(Integer userId);
}

