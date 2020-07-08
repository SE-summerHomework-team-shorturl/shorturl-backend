package com.example.shorturl.service.impl;

import com.example.shorturl.dto.Message;
import com.example.shorturl.entity.User;
import com.example.shorturl.service.LoginService;
import com.example.shorturl.misc.ShorturlUserDetails;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class LoginServiceImpl implements LoginService {
    @Override
    public Message login() {
        User user = ((ShorturlUserDetails) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal()).getUser();
        return new Message("SUCCESS", user);
    }
}
