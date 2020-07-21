package com.example.sharedentity.dao;


import com.example.sharedentity.repository.UserRepository;
import com.example.sharedentity.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    @Override
    public List<User> findAll() {
        return userRepo.findAll();
    }
}
