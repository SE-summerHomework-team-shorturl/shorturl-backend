package com.example.urlshortener.auth.dao.impl;

import com.example.urlshortener.auth.entity.User;
import com.example.urlshortener.auth.dao.UserDao;
import com.example.urlshortener.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao {
    @Autowired
    private UserRepository userRepo;

    @Override
    public User findOneByUsername(String username) {
        return userRepo.findOneByUsername(username).orElse(null);
    }
}
