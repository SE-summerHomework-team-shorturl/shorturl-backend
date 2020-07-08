package com.example.shorturl.dao;

import com.example.shorturl.entity.User;

public interface UserDao {
    boolean existsByUsername(String username);
    User findOneByUsername(String username);
    User save(User user);
}
