package com.example.shorturl.manager.service;

import com.example.shorturl.util.dto.Message;

public interface ShortUrlService {
    Message findAllMyShortUrls(int page, int size);
    Message deleteMyShortUrlById(long id);
}
