package com.example.shorturl.user.dao;

import com.example.shorturl.user.entity.User;
import com.example.shorturl.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao {
    @Autowired
    private UserRepository userRepo;

    @Override
    public boolean existsByUsername(String username) {
        return userRepo.existsByUsername(username);
    }

    @Override
    public void save(User user) {
        userRepo.save(user);
    }
}
