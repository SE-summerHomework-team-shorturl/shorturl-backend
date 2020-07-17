package com.example.addurlservice.service;

import com.example.addurlservice.configuration.MyUserDetailsService;
import com.example.misc.UrlShortenerUserDetails;
import com.example.sharedentity.dao.ShortUrlDao;
import com.example.sharedentity.dto.Message;
import com.example.sharedentity.entity.ShortUrl;
import com.example.sharedentity.entity.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithSecurityContext;
import org.springframework.security.test.context.support.WithSecurityContextFactory;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


@AutoConfigureMockMvc
@SpringBootTest
@ActiveProfiles("test")
@Transactional
@ContextConfiguration
@ExtendWith(SpringExtension.class)
class AddUrlServiceTest {
    @Autowired
    private AddUrlService addUrlService;
    @MockBean
    private ShortUrlDao shortUrlDao;

    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @DisplayName("returnSucessWhenRightAdd")
    @WithUserDetails(value = "test",userDetailsServiceBeanName = "userDetailsService")
    void addToUserShortUrls() throws Exception {
        String testUrl="http://www.baidu.com";
        ShortUrl shortUrl=new ShortUrl(testUrl,1);
        when(shortUrlDao.saveAndFlush(shortUrl)).thenReturn(shortUrl);
        ShortUrl afterUrl=(ShortUrl)(addUrlService.addToMyShortUrls(testUrl)).getBody();
        assertEquals(shortUrl.getUserId(), afterUrl.getUserId());
        assertEquals(shortUrl.getUrl(), afterUrl.getUrl());
    }
}
