package com.example.registerservice.service;

import com.example.sharedentity.dto.Message;
import com.example.sharedentity.entity.User;

import java.util.Map;

public interface RegisterService {
    Message register(Map<String, String> map);
}
