package com.example.redirectservice.service;

import com.example.sharedentity.dao.ShortUrlDao;
import com.example.sharedentity.entity.ShortUrl;
import com.example.sharedentity.util.Base62Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.Timer;

@Service
@Transactional(rollbackFor = Exception.class)
public class RedirectServiceImpl implements RedirectService {
    @Autowired
    private ShortUrlDao shortUrlDao;
    @Autowired
    private ISendeService sendService;

    static ThreadLocal<ArrayList<Long>> threadStorage;

    @Override
    public ShortUrl findShortUrlByToken(String token) throws Exception {
        long shortUrlId = Base62Encoder.decode(token);
        ShortUrl shortUrl = shortUrlDao.findById(shortUrlId);
        if(threadStorage == null)
            threadStorage = new ThreadLocal<>();
        ArrayList<Long> payloadList = threadStorage.get();
        if(payloadList == null) {
            System.out.println("Current Thread is"+Thread.currentThread().getId());
            payloadList = new ArrayList<>();
            threadStorage.set(payloadList);
        }
        payloadList.add(shortUrlId);
        if(payloadList.size()>=100)
        {
            sendService.send().send(MessageBuilder
                    .withPayload(payloadList)
                    .build());
            payloadList.clear();
        }
        return shortUrl;
    }
    /*
    @Scheduled(fixedDelay = 5000)
    void sendListMessage()
    {
        if(threadStorage == null) return;
        ArrayList<Long> payloadList = threadStorage.get();
        if(payloadList == null) return;
        System.out.println(payloadList.size());
        sendService.send().send(MessageBuilder
                .withPayload(payloadList)
                .build());
        payloadList.clear();
        threadStorage.set(payloadList);
    }*/
}
