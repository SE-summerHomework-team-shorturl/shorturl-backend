package com.hcmony.userservice.service.impl;


import com.example.sharedentity.dto.Message;
import com.example.sharedentity.entity.User;
import com.hcmony.userservice.service.LoginService;
import com.hcmony.userservice.misc.ShorturlUserDetails;
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
