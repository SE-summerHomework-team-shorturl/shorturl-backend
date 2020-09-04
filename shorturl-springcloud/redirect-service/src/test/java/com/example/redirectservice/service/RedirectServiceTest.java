package com.example.redirectservice.service;

import com.example.sharedentity.dao.ShortUrlDao;
import com.example.sharedentity.entity.ShortUrl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
@SpringBootTest
class RedirectServiceTest {
    @Autowired
    private RedirectService redirectService;

    @MockBean
    private ShortUrlDao shortUrlDao;

    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @DisplayName("shouldSuccessWhenRightToken")
    void findShortUrlByToken() throws Exception {
        String testToken="1";
        long testId=1;
        ShortUrl shortUrl= new ShortUrl("test1.com",1L);
        when(shortUrlDao.findById(testId)).thenReturn(shortUrl);
        assertEquals(shortUrl,redirectService.findShortUrlByToken(testToken));
    }
}
