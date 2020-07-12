package com.example.shorturl.dao;

import com.example.shorturl.entity.User;

public interface UserDao {
    User findByUsername(String username);
    User add(User user);
}
