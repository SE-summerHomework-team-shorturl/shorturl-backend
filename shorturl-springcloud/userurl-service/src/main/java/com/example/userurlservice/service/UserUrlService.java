package com.example.userurlservice.service;

import com.example.sharedentity.dto.Message;

public interface UserUrlService {
    Message findAllMyShortUrls(int page, int size,int userId);
    Message deleteMyShortUrlById(int id,int userId);
}
