package com.example.shorturl.user.service;

import com.example.shorturl.user.dao.SequenceDao;
import com.example.shorturl.user.dao.UserDao;
import com.example.shorturl.user.entity.User;
import com.example.shorturl.util.dto.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private SequenceDao sequenceDao;

    @Override
    public Message register(User user) {
        long userId = sequenceDao.addAndGetByName("user_id");

        user.setId(userId);
        user.setAdmin(false);
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));

        user = userDao.findByUsernameOrCreate(user);
        if (user.getId() != userId)
            return new Message("DUP_USERNAME", null);

        return new Message("SUCCESS", null);
    }
}
