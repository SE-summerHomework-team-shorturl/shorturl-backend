package com.example.registerservice.service;

import com.example.sharedentity.dto.Message;
import com.example.sharedentity.entity.User;

public interface RegisterService {
    Message register(User user);
}
