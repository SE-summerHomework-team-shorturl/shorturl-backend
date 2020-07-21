package com.example.sharedentity.dao;


import com.example.sharedentity.entity.User;

public interface UserDao {
    boolean existsByUsername(String username);
    User findOneByUsername(String username);
    void save(User user);
}
