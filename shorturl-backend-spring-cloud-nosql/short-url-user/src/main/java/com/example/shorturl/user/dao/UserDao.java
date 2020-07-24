package com.example.shorturl.user.dao;

import com.example.shorturl.user.entity.User;

public interface UserDao {
    User findByUsernameOrCreate(User user);
}
