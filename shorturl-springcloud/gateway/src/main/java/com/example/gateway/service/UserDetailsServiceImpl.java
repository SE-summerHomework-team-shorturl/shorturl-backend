package com.example.gateway.service;


import com.example.sharedentity.dao.UserDao;
import com.example.gateway.misc.ShortUrlUserDetails;
import com.example.sharedentity.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
