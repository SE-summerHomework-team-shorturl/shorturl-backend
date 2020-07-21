package com.example.shorturl.manager.service;

import com.example.shorturl.util.dto.Message;

public interface UserUrlService {
    Message findAllMyShortUrls(int page, int size);
    Message deleteMyShortUrlById(int id);
}
