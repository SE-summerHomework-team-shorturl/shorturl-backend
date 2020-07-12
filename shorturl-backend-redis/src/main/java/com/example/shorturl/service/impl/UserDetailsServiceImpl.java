package com.example.shorturl.service.impl;

import com.example.shorturl.dao.UserDao;
import com.example.shorturl.entity.User;
import com.example.shorturl.misc.ShortUrlUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Primary
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        User user = userDao.findByUsername(username);
        if (user == null)
            throw new UsernameNotFoundException("Username not found");
        return new ShortUrlUserDetails(user);
    }
}
