package com.example.addurlservice.service;

import com.example.sharedentity.dto.Message;
import com.example.sharedentity.entity.ShortUrl;

public interface AddUrlService {
    Message addToMyShortUrls(String url);
}
