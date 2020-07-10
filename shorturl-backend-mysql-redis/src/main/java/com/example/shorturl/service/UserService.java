package com.example.shorturl.service;

import com.example.shorturl.dto.Message;
import com.example.shorturl.entity.User;

public interface UserService {
    Message register(User user);
}
