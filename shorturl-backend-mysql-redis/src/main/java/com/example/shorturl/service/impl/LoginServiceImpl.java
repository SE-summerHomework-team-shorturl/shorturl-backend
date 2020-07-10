package com.example.shorturl.service.impl;

import com.example.shorturl.dto.Message;
import com.example.shorturl.entity.User;
import com.example.shorturl.service.LoginService;
import com.example.shorturl.misc.ShortUrlUserDetails;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LoginServiceImpl implements LoginService {
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Message login() {
        User user = ((ShortUrlUserDetails) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal()).getUser();
        return new Message("SUCCESS", user);
    }
}
