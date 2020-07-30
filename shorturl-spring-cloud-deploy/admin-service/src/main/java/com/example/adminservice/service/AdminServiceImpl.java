package com.example.adminservice.service;

import com.example.misc.UrlShortenerUserDetails;
import com.example.sharedentity.dao.ShortUrlDao;
import com.example.sharedentity.dao.UserDao;
import com.example.sharedentity.dto.Message;
import com.example.sharedentity.entity.ShortUrl;
import com.example.sharedentity.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class AdminServiceImpl implements AdminService {
    @Autowired private ShortUrlDao shortUrlDao;
    @Autowired private UserDao userDao;

    @Override
    public Message findAllUsers() {
        List<User> users = userDao.findAll();
        return new Message(Message.Success_Msg, users);
    }

    @Override
    public Message findAllShortUrls() {
        List<ShortUrl> shortUrls = shortUrlDao.findAll();
        return new Message(Message.Success_Msg, shortUrls);
    }

    @Override
    public Message deleteShortUrlById(Integer id) {
        shortUrlDao.deleteById(id);
        return new Message(Message.Success_Msg, null);
    }
}
