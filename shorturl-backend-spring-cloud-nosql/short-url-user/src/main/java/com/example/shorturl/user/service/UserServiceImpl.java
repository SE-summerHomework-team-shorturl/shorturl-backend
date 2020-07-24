package com.example.shorturl.user.service;

import com.example.shorturl.user.dao.UserDao;
import com.example.shorturl.user.entity.User;
import com.example.shorturl.user.misc.DataCenterFactory;
import com.example.shorturl.util.dto.Message;
import com.example.shorturl.util.misc.DataCenter;
import com.example.shorturl.util.misc.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private DataCenterFactory dataCenterFactory;

    @Override
    public Message register(User user) {
        DataCenter dataCenter = dataCenterFactory.getInstance();
        IdWorker idWorker = dataCenter.pickAnIdWorker();
        long userId = idWorker.nextId();

        user.setId(userId);
        user.setAdmin(false);
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));

        user = userDao.findByUsernameOrCreate(user);
        if (user.getId() != userId)
            return new Message("DUP_USERNAME", null);

        return new Message("SUCCESS", null);
    }
}
