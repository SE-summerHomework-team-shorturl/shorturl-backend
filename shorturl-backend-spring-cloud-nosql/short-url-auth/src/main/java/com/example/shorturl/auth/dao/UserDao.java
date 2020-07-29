package com.example.shorturl.auth.dao;

import com.example.shorturl.auth.entity.User;

public interface UserDao {
    User findByUsername(String username);
}
