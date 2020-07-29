package com.example.statisticservice.service;

import com.example.sharedentity.dao.ShortUrlDao;
import com.example.sharedentity.entity.ShortUrl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import static org.mockito.Mockito.*;

@SpringBootTest
@ActiveProfiles("test")
class ReceiverServiceTest {
    @Autowired
    ReceiverService receiverService;
    @MockBean
    private ShortUrlDao shortUrlDao;
    @Test
    void onReceiver() {
        ShortUrl testUrl= new ShortUrl(1L,"test1.com",1L);
        testUrl.setClicks(0);
        ShortUrl testUrl2= new ShortUrl(1L,"test1.com",1L);
        testUrl2.setClicks(1);
        when(shortUrlDao.findById(1)).thenReturn(testUrl);
        when(shortUrlDao.saveAndFlush(testUrl2)).thenReturn(testUrl2);
        receiverService.onReceiver(1);
        verify(shortUrlDao, times(1)).saveAndFlush(testUrl2);
    }
}
