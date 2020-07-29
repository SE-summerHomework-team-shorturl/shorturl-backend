package com.example.shorturl.auth.service;

import com.example.shorturl.auth.dao.UserDao;
import com.example.shorturl.auth.entity.User;
import com.example.shorturl.auth.misc.ShortUrlUserDetails;
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
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findByUsername(username);

        if (user == null)
            throw new UsernameNotFoundException("Username not found");

        return new ShortUrlUserDetails(user);
    }
}
