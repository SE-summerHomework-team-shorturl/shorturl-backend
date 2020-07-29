package com.example.shorturl.shortener.service;

import com.example.shorturl.util.dto.Message;

public interface ShortUrlService {
    Message addToMyShortUrls(String url);
}
