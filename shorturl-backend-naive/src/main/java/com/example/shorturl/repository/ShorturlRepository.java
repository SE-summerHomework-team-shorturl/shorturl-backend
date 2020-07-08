package com.example.shorturl.repository;

import com.example.shorturl.entity.Shorturl;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShorturlRepository extends JpaRepository<Shorturl, Integer> {
    List<Shorturl> findAllByUserId(Integer userId);
}
