package com.example.shorturl.service.impl;

import com.example.shorturl.dao.CounterDao;
import com.example.shorturl.dao.UserDao;
import com.example.shorturl.dto.Message;
import com.example.shorturl.entity.Counter;
import com.example.shorturl.entity.User;
import com.example.shorturl.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired private UserDao userDao;
    @Autowired private CounterDao counterDao;

    @Override
    public Message register(User user) {
        if (userDao.existsByUsername(user.getUsername()))
            return new Message("DUP_USERNAME", null);
        Query query = new Query(Criteria.where("_id").is(Counter.CounterId.USER_ID.ordinal()));
        Update update = new Update().inc("seq", 1);
        Counter counter = counterDao.findAndModify(query, update);
        user.setId(counter.getSeq());
        user.setAdmin(false);
        user = userDao.save(user);
        return new Message("SUCCESS", user);
    }
}
