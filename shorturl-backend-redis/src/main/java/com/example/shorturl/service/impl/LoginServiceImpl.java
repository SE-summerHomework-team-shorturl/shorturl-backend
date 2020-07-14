package com.example.shorturl.service.impl;

import com.example.shorturl.dto.Message;
import com.example.shorturl.entity.User;
import com.example.shorturl.misc.ShortUrlUserDetails;
import com.example.shorturl.service.LoginService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {
    @Override
    public Message login() {
        User user = ((ShortUrlUserDetails) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal()).getUser();
        return new Message("SUCCESS", user);
    }
}
