package com.example.registerservice.service;

import com.example.sharedentity.dao.UserDao;
import com.example.sharedentity.dto.Message;
import com.example.sharedentity.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
@Transactional(rollbackFor = Exception.class)
public class RegisterServiceImpl implements RegisterService {
    @Autowired
    private UserDao userDao;

    @Override
    public Message register(Map<String, String> map) {
        User user = new User();
        user.setEmail(map.get("email"));
        user.setUsername(map.get("username"));
        user.setPassword(new BCryptPasswordEncoder().encode(map.get("password")));

        if (userDao.existsByUsername(user.getUsername()))
            return new Message(Message.Dup_User_Msg, null);
        user.setAdmin(false);
        userDao.save(user);
        return new Message(Message.Success_Msg, null);
    }
}
