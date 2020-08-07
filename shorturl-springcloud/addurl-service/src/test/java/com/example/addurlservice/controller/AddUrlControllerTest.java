package com.example.addurlservice.controller;

import com.example.addurlservice.AddurlserviceApplication;
import com.example.addurlservice.service.AddUrlService;

import com.example.misc.*;
import com.example.sharedentity.dto.Message;
import com.example.sharedentity.entity.ShortUrl;
import com.example.sharedentity.entity.User;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.context.WebApplicationContext;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@AutoConfigureMockMvc
@SpringBootTest
@ActiveProfiles("test")
@ContextConfiguration
class AddUrlControllerTest {
    @Autowired
    private AddUrlController addUrlController;
    @MockBean
    private AddUrlService addUrlService;

    @Test
    @DisplayName("returnSucessWhenRightUrlUser")
    void addToMyShortUrls() throws Exception {
        String testInfo="test info";
        String testUrl = "test://url";
        Message testMsg = new Message(testInfo,"test 1");
        ShortUrl shortUrl=new ShortUrl(1L,testUrl,1L);
        when( addUrlService.addToMyShortUrls(testUrl)).thenReturn(testMsg);
        assertEquals(addUrlController.addToMyShortUrls(testUrl),testMsg);
    }
}
