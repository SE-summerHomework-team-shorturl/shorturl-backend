package com.example.userurlservice.service;

import com.example.sharedentity.dao.ShortUrlDao;
import com.example.sharedentity.dto.Message;
import com.example.sharedentity.entity.ShortUrl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@AutoConfigureMockMvc
@SpringBootTest
@Transactional
@ContextConfiguration
@ExtendWith(SpringExtension.class)
class UserUrlServiceTest {
    @Autowired
    private UserUrlService userUrlService;
    @MockBean
    private ShortUrlDao shortUrlDao;

    @Mock
    private Authentication auth;

    @BeforeEach
    void setUp() {
        Map<String,Object> headerMap = new HashMap<>();
        headerMap.put("fake_header","fake_header");
        Map<String,Object> map = new HashMap<>();
        map.put("user_name","1");
        Jwt jwt = new Jwt("faketoken", Instant.MIN,Instant.MAX,headerMap,map);
        when(auth.getCredentials()).thenReturn(jwt);
        SecurityContextHolder.getContext().setAuthentication(auth);
    }
    @AfterEach
    void tearDown() {
    }

    @Test
    @DisplayName("returnSuccessWhenRightFind")
    @WithUserDetails(value = "test",userDetailsServiceBeanName = "userDetailsService")
    void addToUserShortUrls() throws Exception {
        String testUrl="http://www.baidu.com";
        List<ShortUrl> shortUrls = new ArrayList<ShortUrl>();
        shortUrls.add(0,new ShortUrl(1L,testUrl,1L));
        when(shortUrlDao.findAllByUserIdFetchStat(1)).thenReturn(shortUrls);
        List<ShortUrl> afterUrls=(List<ShortUrl>)(userUrlService.findAllMyShortUrls()).getBody();
        assertEquals(shortUrls, afterUrls);
    }

    @Test
    @DisplayName("returnSuccessWhenDeleteSucceed")
    @WithUserDetails(value = "test",userDetailsServiceBeanName = "userDetailsService")
    void deleteUserShortUrlsSuccess() {
        String testUrl="http://www.baidu.com";
        ShortUrl shortUrl = new ShortUrl(1L,testUrl,1L);
        when(shortUrlDao.findById(1)).thenReturn(shortUrl);
        String status=userUrlService.deleteMyShortUrlById(1).getStatus();
        assertEquals(Message.Success_Msg, status);
    }

    @Test
    @DisplayName("returnSuccessWhenDeleteFail")
    @WithUserDetails(value = "test",userDetailsServiceBeanName = "userDetailsService")
    void deleteUserShortUrlsFailed() {
        String testUrl="http://www.baidu.com";
        ShortUrl shortUrl = new ShortUrl(1L,testUrl,1L);
        when(shortUrlDao.findById(1)).thenReturn(null);
        String status=userUrlService.deleteMyShortUrlById(1).getStatus();
        assertEquals(Message.No_URL_Msg, status);
    }

    @Test
    @DisplayName("returnSuccessWhenDeleteOthers")
    @WithUserDetails(value = "test",userDetailsServiceBeanName = "userDetailsService")
    void deleteUserShortUrlsOthers() {
        String testUrl="http://www.baidu.com";
        ShortUrl shortUrl = new ShortUrl(1L,testUrl,2L);
        when(shortUrlDao.findById(1)).thenReturn(shortUrl);
        String status=userUrlService.deleteMyShortUrlById(1).getStatus();
        assertEquals(Message.Not_Your_URL_Msg, status);
    }
}
