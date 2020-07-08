package com.example.shorturl.service;

import com.example.shorturl.dto.Message;
import com.example.shorturl.entity.Shorturl;

public interface ShorturlService {
    Message addToMyShorturls(String url);
    Message findAllMyShorturls();
    Message deleteMyShorturlById(int id);
}
