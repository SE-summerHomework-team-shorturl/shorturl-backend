package com.example.userurlservice.service;

import com.example.sharedentity.dao.ShortUrlDao;
import com.example.sharedentity.dto.Message;
import com.example.sharedentity.entity.ShortUrl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.*;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@AutoConfigureMockMvc
@SpringBootTest
@ActiveProfiles("test")
@Transactional
@ContextConfiguration
@ExtendWith(SpringExtension.class)
class UserUrlServiceTest {
    @Autowired
    private UserUrlService userUrlService;
    @MockBean
    private ShortUrlDao shortUrlDao;

    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @DisplayName("returnSucessWhenRightFind")
    @WithUserDetails(value = "test",userDetailsServiceBeanName = "userDetailsService")
    void addToUserShortUrls() throws Exception {
        String testUrl="http://www.baidu.com";
        List<ShortUrl> shortUrls = new ArrayList<ShortUrl>();
        shortUrls.add(0,new ShortUrl(1,testUrl,1));
        when(shortUrlDao.findAllByUserId(1)).thenReturn(shortUrls);
        List<ShortUrl> afterUrls=(List<ShortUrl>)(userUrlService.findAllMyShortUrls()).getBody();
        assertEquals(shortUrls, afterUrls);
    }

    @Test
    @DisplayName("returnSucessWhenDeleteSucceed")
    @WithUserDetails(value = "test",userDetailsServiceBeanName = "userDetailsService")
    void deleteUserShortUrlsSuccess() {
        String testUrl="http://www.baidu.com";
        ShortUrl shortUrl = new ShortUrl(1,testUrl,1);
        when(shortUrlDao.findById(1)).thenReturn(shortUrl);
        String status=userUrlService.deleteMyShortUrlById(1).getStatus();
        assertEquals(Message.Success_Msg, status);
    }

    @Test
    @DisplayName("returnSucessWhenDeleteFail")
    @WithUserDetails(value = "test",userDetailsServiceBeanName = "userDetailsService")
    void deleteUserShortUrlsFailed() {
        String testUrl="http://www.baidu.com";
        ShortUrl shortUrl = new ShortUrl(1,testUrl,1);
        when(shortUrlDao.findById(1)).thenReturn(null);
        String status=userUrlService.deleteMyShortUrlById(1).getStatus();
        assertEquals(Message.No_URL_Msg, status);
    }

    @Test
    @DisplayName("returnSucessWhenDeleteOthers")
    @WithUserDetails(value = "test",userDetailsServiceBeanName = "userDetailsService")
    void deleteUserShortUrlsOthers() {
        String testUrl="http://www.baidu.com";
        ShortUrl shortUrl = new ShortUrl(1,testUrl,2);
        when(shortUrlDao.findById(1)).thenReturn(shortUrl);
        String status=userUrlService.deleteMyShortUrlById(1).getStatus();
        assertEquals(Message.Not_Your_URL_Msg, status);
    }
}
