package com.example.adminservice.service;

import com.example.sharedentity.dto.Message;

public interface AdminService {
    Message findAllUsers();
    Message findAllShortUrls();
    Message deleteShortUrlById(long id);
}
