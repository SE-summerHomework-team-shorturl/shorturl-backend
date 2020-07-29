package com.example.authserver.service;

import com.example.misc.UrlShortenerUserDetails;
import com.example.sharedentity.dao.UserDao;
import com.example.sharedentity.entity.ShortUrl;
import com.example.sharedentity.entity.User;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
class UserDetailServiceTest {
    @Autowired
    private UserDetailsService userDetailsService;

    @MockBean
    private UserDao userDao;

    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @Order(1)
    @DisplayName("shouldSuccessWhenRightToken")
    void findShortUrlByToken(){
        User user= new User();
        user.setId(1L);
        user.setUsername("1");
        when(userDao.findOneByUsername("1")).thenReturn(user);
        assertEquals("1", userDetailsService.loadUserByUsername("1").getUsername());
    }

    @Test
    @Order(2)
    @DisplayName("shouldExceptionWhenWrongToken")
    void findShortUrlByTokenFailed(){
        User user= new User();
        user.setId(1L);
        user.setUsername("1");
        when(userDao.findOneByUsername("1")).thenReturn(null);
        Throwable exception = assertThrows(Exception.class, ()->userDetailsService.loadUserByUsername("1"));
        assertEquals("Username Not Found", exception.getMessage());
    }
}
