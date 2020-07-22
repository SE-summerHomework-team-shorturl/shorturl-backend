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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@AutoConfigureMockMvc
@SpringBootTest
@ActiveProfiles("test")
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
    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @DisplayName("returnSucessWhenRightFindUsers")
    void findAllUsers() throws Exception {
        List<User> users = new ArrayList<User>();
        users.add(new User(1,"bill","1234","bill@1234.com",false));
        when(userDao.findAll()).thenReturn(users);
        assertEquals(Message.Success_Msg,adminService.findAllUsers().getStatus());
        assertEquals(users,adminService.findAllUsers().getBody());
    }

    @Test
    @DisplayName("returnSucessWhenRightFindShortUrls")
    void findAllShortUrls() throws Exception {
        List<ShortUrl> shorturls = new ArrayList<ShortUrl>();
        shorturls.add(new ShortUrl(1,"http://www.baidu.com",1));
        when(shortUrlDao.findAll()).thenReturn(shorturls);
        assertEquals(Message.Success_Msg,adminService.findAllShortUrls().getStatus());
        assertEquals(shorturls,adminService.findAllShortUrls().getBody());
    }

    @Test
    @DisplayName("returnSucessWhenDelete")
    void deleteUserShortUrlsOthers() {
        String status=adminService.deleteShortUrlById(1).getStatus();
        assertEquals(Message.Success_Msg, status);
    }
}
