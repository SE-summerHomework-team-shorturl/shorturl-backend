package com.example.shorturl.auth.dao;

import com.example.shorturl.auth.entity.User;
import com.example.shorturl.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User findByUsername(String username) {
        return userRepository.findOneByUsername(username).orElse(null);
    }
}
