package com.example.loginservice.service;

import com.example.sharedentity.dao.ShortUrlDao;
import com.example.sharedentity.dao.UserDao;
import com.example.sharedentity.dto.Message;
import com.example.sharedentity.entity.ShortUrl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class LoginServiceImpl implements LoginService {
    @Autowired private UserDao userDao;

    @Override
    public Message login() {
        return new Message("SUCCESS",null);
    }
}
