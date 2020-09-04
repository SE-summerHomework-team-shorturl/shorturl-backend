package com.example.userurlservice.service;

import com.example.sharedentity.dto.Message;

public interface UserUrlService {
    Message findAllMyShortUrls();
    Message deleteMyShortUrlById(long id);
}
