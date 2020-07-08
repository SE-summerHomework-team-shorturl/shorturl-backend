package com.hcmony.userservice.service.impl;

import com.example.sharedentity.entity.User;
import com.hcmony.userservice.dao.UserDao;

import com.hcmony.userservice.misc.ShorturlUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Primary
@Transactional(rollbackFor = Exception.class)
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired private UserDao userDao;


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userDao.findOneByUsername(s);
        if (user == null)
            throw new UsernameNotFoundException("");
        return new ShorturlUserDetails(user);
    }
}
