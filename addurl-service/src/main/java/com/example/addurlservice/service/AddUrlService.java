package com.example.addurlservice.service;

import com.example.sharedentity.dto.Message;

public interface AddUrlService {
    Message addToMyShortUrls(String url);
}
