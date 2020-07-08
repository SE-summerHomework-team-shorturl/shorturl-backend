package com.example.shorturl.service.impl;

import com.example.shorturl.dao.UserDao;
import com.example.shorturl.dto.Message;
import com.example.shorturl.entity.User;
import com.example.shorturl.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {
    @Autowired private UserDao userDao;

    @Override
    public Message register(User user) {
        if (userDao.existsByUsername(user.getUsername()))
            return new Message("DUP_USERNAME", null);
        user.setAdmin(false);
        userDao.save(user);
        return new Message("SUCCESS", null);
    }
}
