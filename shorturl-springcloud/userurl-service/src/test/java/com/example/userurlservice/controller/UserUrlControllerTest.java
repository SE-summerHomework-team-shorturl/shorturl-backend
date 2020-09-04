package com.example.userurlservice.controller;

import com.example.sharedentity.dto.Message;
import com.example.sharedentity.entity.User;
import com.example.userurlservice.service.UserUrlService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@AutoConfigureMockMvc
@SpringBootTest
@Transactional
class UserUrlControllerTest{
    @Autowired
    private UserUrlController userUrlController;
    @MockBean
    private UserUrlService userUrlService;

    @Test
    @DisplayName("shouldSuccessWhenRightInput")
    void findAllMyShortUrls() throws Exception {
        String testInfo="test info";
        Message testMsg = new Message(testInfo,"test 1");
        when(userUrlService.findAllMyShortUrls()).thenReturn(testMsg);
        assertEquals(userUrlController.findAllMyShortUrls(), testMsg);
    }

    @Test
    @DisplayName("shouldSuccessWhenRightInput")
    void deleteMyShortUrlById() throws Exception {
        String testInfo="test info";
        Message testMsg = new Message(testInfo,"test 2");
        when(userUrlService.deleteMyShortUrlById(1L)).thenReturn(testMsg);
        assertEquals(userUrlController.deleteMyShortUrlById(1L), testMsg);
    }
}
