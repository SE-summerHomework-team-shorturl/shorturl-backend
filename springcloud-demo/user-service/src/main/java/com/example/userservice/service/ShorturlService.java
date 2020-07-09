package com.example.userservice.service;


import com.example.sharedentity.dto.Message;

public interface ShorturlService {
    Message addToMyShorturls(String url);
    Message findAllMyShorturls();
    Message deleteMyShorturlById(int id);
}
