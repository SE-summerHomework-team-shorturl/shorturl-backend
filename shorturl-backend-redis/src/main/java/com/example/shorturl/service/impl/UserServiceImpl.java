package com.example.shorturl.service.impl;

import com.example.shorturl.dao.UserDao;
import com.example.shorturl.dto.Message;
import com.example.shorturl.entity.User;
import com.example.shorturl.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public Message register(User user) {
        user.setAdmin(false);
        if ((user = userDao.add(user)) == null)
            return new Message("DUP_USERNAME", null);
        return new Message("SUCCESS", user);
    }
}
