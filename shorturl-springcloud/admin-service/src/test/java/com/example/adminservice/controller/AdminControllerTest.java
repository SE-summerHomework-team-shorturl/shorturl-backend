package com.example.adminservice.controller;

import com.example.adminservice.service.AdminService;
import com.example.sharedentity.dto.Message;
import com.example.sharedentity.entity.User;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@AutoConfigureMockMvc
@SpringBootTest
@ActiveProfiles("test")
@Transactional
class AdminControllerTest {
    @Autowired
    private AdminController adminController;
    @MockBean
    private AdminService adminService;
    
    @Test
    @WithMockUser(authorities ="ROLE_ADMIN")
    @DisplayName("shouldSuccessWhenRightInput")
    void findAllShortUrls() throws Exception {
        String testInfo="test info";
        Message testMsg = new Message(testInfo,"test 1");
        when( adminService.findAllShortUrls()).thenReturn(testMsg);
        assertEquals(adminController.findAllShortUrls(),testMsg);
    }

    @Test
    @WithMockUser(authorities ="ROLE_ADMIN")
    @DisplayName("shouldSuccessWhenRightInput")
    void findAllUsers() throws Exception {
        String testInfo="test info";
        Message testMsg = new Message(testInfo,"test 2");
        when( adminService.findAllUsers()).thenReturn(testMsg);
        assertEquals(adminController.findAllUsers(),testMsg);
    }

    @Test
    @WithMockUser(authorities ="ROLE_ADMIN")
    @DisplayName("shouldSuccessWhenRightInput")
    void deleteShortUrlById() throws Exception {
        String testInfo="test info";
        Message testMsg = new Message(testInfo,"test 3");
        when( adminService.deleteShortUrlById(1L)).thenReturn(testMsg);
        assertEquals(adminController.deleteShortUrlById(1L),testMsg);
    }
}
