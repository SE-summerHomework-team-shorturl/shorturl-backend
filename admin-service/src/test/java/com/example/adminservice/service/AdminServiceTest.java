package com.example.adminservice.service;

import com.example.sharedentity.dao.ShortUrlDao;
import com.example.sharedentity.dao.UserDao;
import com.example.sharedentity.dto.Message;
import com.example.sharedentity.entity.ShortUrl;
import com.example.sharedentity.entity.User;
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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@AutoConfigureMockMvc
@SpringBootTest
@Transactional
@ContextConfiguration
@ExtendWith(SpringExtension.class)
class AdminServiceTest {
    @Autowired
    private AdminService adminService;
    @MockBean
    private ShortUrlDao shortUrlDao;
    @MockBean
    private UserDao userDao;
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
    @DisplayName("returnSuccessWhenRightFindUsers")
    void findAllUsers() throws Exception {
        List<User> users = new ArrayList<User>();
        users.add(new User(1L,"bill","1234","bill@1234.com",false));
        when(userDao.findAll()).thenReturn(users);
        assertEquals(Message.Success_Msg,adminService.findAllUsers().getStatus());
        assertEquals(users,adminService.findAllUsers().getBody());
    }

    @Test
    @DisplayName("returnSuccessWhenRightFindShortUrls")
    void findAllShortUrls() throws Exception {
        List<ShortUrl> shorturls = new ArrayList<ShortUrl>();
        shorturls.add(new ShortUrl(1L,"http://www.baidu.com",1L));
        when(shortUrlDao.findAllFetchStat()).thenReturn(shorturls);
        assertEquals(Message.Success_Msg,adminService.findAllShortUrls().getStatus());
        assertEquals(shorturls,adminService.findAllShortUrls().getBody());
    }

    @Test
    @DisplayName("returnSuccessWhenDelete")
    void deleteUserShortUrlsOthers() {
        String status=adminService.deleteShortUrlById(1).getStatus();
        assertEquals(Message.Success_Msg, status);
    }
}
