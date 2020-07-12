package com.example.shorturl.dao.impl;

import com.example.shorturl.dao.UserDao;
import com.example.shorturl.entity.User;
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
        ValueOperations<String, String> valueOps = template.opsForValue();

        String userIdStr = valueOps.get("username.index:" + username);
        if (userIdStr == null)
            return null;
        long userId = Long.parseLong(userIdStr);

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

        Boolean usernameNotExists = valueOps.
                setIfAbsent("username.index:" + user.getUsername(), "0");
        assert usernameNotExists != null;
        if (!usernameNotExists)
            return null;

        Long userId = valueOps.increment("seq:user.id");
        assert userId != null;
        user.setId(userId);

        valueOps.set("username.index:" + user.getUsername(), userId.toString());

        Map<String, String> map = new HashMap<>();
        map.put("username", user.getUsername());
        map.put("password", user.getPassword());
        map.put("email", user.getEmail());
        map.put("admin", user.getAdmin().toString());
        hashOps.putAll("user:" + userId, map);

        return user;
    }
}
