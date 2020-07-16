package com.example.urlshortener.auth.dao;

import com.example.urlshortener.auth.entity.User;

public interface UserDao {
    User findOneByUsername(String username);
}
