package com.example.shorturl.dao.impl;

import com.example.shorturl.dao.UserDao;
import com.example.shorturl.entity.User;
import com.example.shorturl.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao {
    @Autowired private UserRepository userRepo;

    @Override
    public boolean existsByUsername(String username) {
        return userRepo.existsByUsername(username);
    }

    @Override
    public User findOneByUsername(String username) {
        return userRepo.findOneByUsername(username).orElse(null);
    }

    @Override
    public void save(User user) {
        userRepo.save(user);
    }
}
