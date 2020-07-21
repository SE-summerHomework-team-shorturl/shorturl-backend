package com.example.addurlservice.configuration;

import com.example.misc.UrlShortenerUserDetails;
import com.example.sharedentity.dao.UserDao;
import com.example.sharedentity.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

@Service
@Primary
public class MyUserDetailsService implements UserDetailsService {
    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = new User();
        user.setId(1);
        user.setUsername("test");
        user.setPassword("test");
        user.setAdmin(false);
        user.setEmail("test@test.com");
        /*
        if (s != user.getUsername())
            throw new UsernameNotFoundException("Username Not Found");*/
        return new UrlShortenerUserDetails(user);
    }
}
