package com.example.shorturl.user.service;

import com.example.shorturl.user.dao.UserDao;
import com.example.shorturl.user.entity.User;
import com.example.shorturl.util.dto.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, rollbackFor = Exception.class)
    public Message register(User user) {
        if (userDao.existsByUsername(user.getUsername()))
            return new Message("DUP_USERNAME", null);

        user.setAdmin(false);
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));

        userDao.save(user);

        return new Message("SUCCESS", null);
    }
}
