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
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Primary
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired private UserDao userDao;

    @Override
    @Transactional(rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED)
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userDao.findOneByUsername(s);
        if (user == null)
            throw new UsernameNotFoundException("");
        return new ShortUrlUserDetails(user);
    }
}
