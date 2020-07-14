package com.example.securitygateway.service;

import com.example.securitygateway.misc.ShortUrlUserDetails;
import com.example.sharedentity.dao.UserDao;
import com.example.sharedentity.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class UserDetailsServiceImpl implements ReactiveUserDetailsService {
    @Autowired private UserDao userDao;

    @Override
    public Mono<UserDetails> findByUsername(String s) {
        User user = userDao.findOneByUsername(s);
        if (user == null)
            return Mono.error(new UsernameNotFoundException("User Not Found"));
        else{
            return Mono.just(new ShortUrlUserDetails(user));
        }
    }
}
