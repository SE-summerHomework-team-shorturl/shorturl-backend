package com.example.shorturl.dao.impl;

import com.example.shorturl.dao.UserDao;
import com.example.shorturl.entity.User;
import com.example.shorturl.misc.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class UserDaoImpl implements UserDao {
    @Autowired
    private StringRedisTemplate template;

    @Override
    public User findByUsername(String username) {
        HashOperations<String, String, String> hashOps = template.opsForHash();

        String userIdString = hashOps.get("username.index", username);
        if (userIdString == null)
            return null;
        long userId = Long.parseLong(userIdString);

        Map<String, String> entries = hashOps.entries("user:" + userId);
        if (entries.isEmpty())
            return null;

        User user = new User();
        user.setId(userId);
        user.setUsername(entries.get("username"));
        user.setPassword(entries.get("password"));
        user.setEmail(entries.get("email"));
        user.setAdmin(entries.get("admin").equals("true"));

        return user;
    }

    @Override
    public User add(User user) {
        HashOperations<String, String, String> hashOps = template.opsForHash();
        ValueOperations<String, String> valueOps = template.opsForValue();

        Boolean usernameNotExists = hashOps.
                putIfAbsent("username.index", user.getUsername(), "0");
        if (!usernameNotExists)
            return null;

        Long userId = valueOps.increment("seq:user.id");
        assert userId != null;
        if (userId > Constant.SAFE_INT_MAX)
            throw new RuntimeException("User id space has run out");
        user.setId(userId);

        hashOps.put("username.index", user.getUsername(), userId.toString());

        Map<String, String> map = new HashMap<>();
        map.put("username", user.getUsername());
        map.put("password", user.getPassword());
        map.put("email", user.getEmail());
        map.put("admin", user.getAdmin().toString());
        hashOps.putAll("user:" + userId, map);

        return user;
    }
}
