package com.example.authserver.service;

import com.example.misc.UrlShortenerUserDetails;
import com.example.sharedentity.dao.UserDao;
import com.example.sharedentity.entity.User;
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
    @Autowired
    private UserDao userDao;

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userDao.findOneByUsername(s);
        if (user == null)
            throw new UsernameNotFoundException("");
        return new UrlShortenerUserDetails(user);
    }
}
