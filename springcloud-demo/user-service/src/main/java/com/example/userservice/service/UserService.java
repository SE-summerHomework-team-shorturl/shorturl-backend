package com.example.userservice.service;


import com.example.sharedentity.dto.Message;
import com.example.sharedentity.entity.User;

public interface UserService {
    Message register(User user);
}
