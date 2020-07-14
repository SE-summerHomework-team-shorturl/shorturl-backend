package com.example.registerservice.service;

import com.example.sharedentity.dao.UserDao;
import com.example.sharedentity.dto.Message;
import com.example.sharedentity.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class RegisterServiceImpl implements RegisterService {
    @Autowired
    private UserDao userDao;

    @Override
    public Message register(User user) {
        if (userDao.existsByUsername(user.getUsername()))
            return new Message("DUP_USERNAME", null);
        user.setAdmin(false);
        userDao.save(user);
        return new Message("SUCCESS", null);
    }
}
