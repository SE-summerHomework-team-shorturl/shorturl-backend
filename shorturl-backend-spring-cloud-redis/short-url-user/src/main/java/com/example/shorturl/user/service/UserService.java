package com.example.shorturl.user.service;

import com.example.shorturl.user.entity.User;
import com.example.shorturl.util.dto.Message;

public interface UserService {
    Message register(User user);
}
