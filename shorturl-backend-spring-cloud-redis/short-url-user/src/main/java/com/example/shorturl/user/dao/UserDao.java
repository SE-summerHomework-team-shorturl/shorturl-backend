package com.example.shorturl.user.dao;

import com.example.shorturl.user.entity.User;

public interface UserDao {
    boolean existsByUsername(String username);
    void save(User user);
}
