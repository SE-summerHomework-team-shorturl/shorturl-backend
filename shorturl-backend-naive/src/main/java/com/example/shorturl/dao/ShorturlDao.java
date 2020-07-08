package com.example.shorturl.dao;

import com.example.shorturl.entity.Shorturl;

import java.util.List;

public interface ShorturlDao {
    Shorturl findById(int id);
    List<Shorturl> findAllByUserId(int userId);
    Shorturl saveAndFlush(Shorturl shorturl);
    void deleteById(int id);
}
