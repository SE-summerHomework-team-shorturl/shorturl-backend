package com.example.addurlservice.service;

import com.example.sharedentity.dao.ShortUrlDao;
import com.example.sharedentity.entity.ShortUrl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@AutoConfigureMockMvc
@SpringBootTest
@ActiveProfiles("test")
@Transactional
class AddUrlServiceTest {
    @Autowired
    private AddUrlService addUrlService;
    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @DisplayName("returnSucessWhenRightUrlUser")
    void addToMyShortUrls() throws Exception {

        String testUrl="http://www.baidu.com";
        Integer testUserId=1;
        ShortUrl shortUrl=new ShortUrl(testUrl,1);

    //    when( shortUrlDao.saveAndFlush(shortUrl)).thenReturn(shortUrl);
        ShortUrl afterUrl=(ShortUrl)addUrlService.addToMyShortUrls(testUrl,testUserId).getBody();
        assertEquals(shortUrl.getUrl(), afterUrl.getUrl());
     //   assertEquals("SUCCESS", addUrlService.addToMyShortUrls(testUrl,testUserId).getStatus());
    }
}
