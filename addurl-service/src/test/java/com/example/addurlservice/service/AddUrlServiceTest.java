package com.example.addurlservice.service;

import com.example.sharedentity.dao.ShortUrlDao;
import com.example.sharedentity.dto.Message;
import com.example.sharedentity.entity.ShortUrl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


@AutoConfigureMockMvc
@SpringBootTest
@Transactional
@ContextConfiguration
@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
class AddUrlServiceTest {
    @Autowired
    private AddUrlService addUrlService;
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
    @DisplayName("returnSuccessWhenRightAdd")
    void addToUserShortUrls() throws Exception {
        String testUrl="http://www.baidu.com";
        ShortUrl shortUrl=new ShortUrl(testUrl,1L);
        when(shortUrlDao.saveAndFlush(shortUrl)).thenReturn(shortUrl);
        Message retMessage = addUrlService.addToMyShortUrls(testUrl);
        assertEquals(Message.Success_Msg, retMessage.getStatus());
        ShortUrl afterUrl=(ShortUrl)(retMessage).getBody();
        assertEquals(shortUrl.getUserId(), afterUrl.getUserId());
        assertEquals(shortUrl.getUrl(), afterUrl.getUrl());
    }
    @Test
    @DisplayName("returnNullWhenInvalidAdd")
    void test2() throws Exception {
        String testUrl="www.baidu.com";
        ShortUrl shortUrl=new ShortUrl(testUrl,1L);
        when(shortUrlDao.saveAndFlush(shortUrl)).thenReturn(shortUrl);
        Message retMessage = addUrlService.addToMyShortUrls(testUrl);
        assertEquals(Message.Invalid_Url_Msg, retMessage.getStatus());
    }
}
