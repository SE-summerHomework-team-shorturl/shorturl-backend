package com.example.sharedentity.dao;


import com.example.sharedentity.entity.User;

import java.util.List;

public interface UserDao {
    boolean existsByUsername(String username);
    User findOneByUsername(String username);
    void save(User user);
    List<User> findAll();
}
