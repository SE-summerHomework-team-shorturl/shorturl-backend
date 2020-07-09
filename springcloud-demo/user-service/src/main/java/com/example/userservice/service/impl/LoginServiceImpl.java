package com.example.userservice.service.impl;


import com.example.sharedentity.dto.Message;
import com.example.sharedentity.entity.User;
import com.example.sharedentity.misc.ShorturlUserDetails;
import com.example.userservice.service.LoginService;
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
