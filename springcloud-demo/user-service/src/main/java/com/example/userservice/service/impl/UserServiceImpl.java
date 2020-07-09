package com.example.userservice.service.impl;


import com.example.sharedentity.dto.Message;
import com.example.sharedentity.entity.User;
import com.example.userservice.dao.UserDao;
import com.example.userservice.service.UserService;
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
