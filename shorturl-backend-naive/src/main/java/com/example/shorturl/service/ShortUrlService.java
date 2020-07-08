package com.example.shorturl.service;

import com.example.shorturl.dto.Message;

public interface ShortUrlService {
    Message addToMyShortUrls(String url);
    Message findAllMyShortUrls();
    Message deleteMyShortUrlById(int id);
}
