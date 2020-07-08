package com.example.shorturl.service.impl;

import com.example.shorturl.dao.CounterDao;
import com.example.shorturl.dao.UserDao;
import com.example.shorturl.dto.Message;
import com.example.shorturl.entity.Counter;
import com.example.shorturl.entity.User;
import com.example.shorturl.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(isolation = Isolation.REPEATABLE_READ, rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {
    @Autowired private UserDao userDao;
    @Autowired private CounterDao counterDao;

    @Override
    public Message register(User user) {
        if (userDao.existsByUsername(user.getUsername()))
            return new Message("DUP_USERNAME", null);
        Counter counter = counterDao.findById(Counter.CounterId.USER_ID.ordinal());
        user.setId(counter.getSeq());
        counter.setSeq(counter.getSeq() + 1);
        user.setAdmin(false);
        user = userDao.save(user);
        counterDao.save(counter);
        return new Message("SUCCESS", user);
    }
}
