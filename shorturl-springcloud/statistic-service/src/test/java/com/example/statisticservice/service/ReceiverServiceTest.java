package com.example.statisticservice.service;

import com.example.sharedentity.dao.ShortUrlDao;
import com.example.sharedentity.dao.UrlStatisticDao;
import com.example.sharedentity.entity.ShortUrl;
import com.example.sharedentity.entity.UrlStatistic;
import io.micrometer.core.instrument.Statistic;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@SpringBootTest
@ActiveProfiles("test")
class ReceiverServiceTest {
    @Autowired
    ReceiverService receiverService;
    @MockBean
    private UrlStatisticDao statisticDao;
    @Test
    void onReceiver() {

        UrlStatistic testUrl= new UrlStatistic(1L);
        UrlStatistic testUrl2= new UrlStatistic(1L);
        testUrl2.setClicks(100);
        List<UrlStatistic> lists = new ArrayList<>();
        lists.add(testUrl2);
        when(statisticDao.findById(1L)).thenReturn(testUrl);
        when(statisticDao.saveAll(any())).thenReturn(lists);
        for(int i=0;i<99;i++)
            receiverService.onReceiver(1L);
        verify(statisticDao, times(0)).saveAll(any());
        receiverService.onReceiver(1L);
        verify(statisticDao, times(1)).saveAll(any());
    }
}
