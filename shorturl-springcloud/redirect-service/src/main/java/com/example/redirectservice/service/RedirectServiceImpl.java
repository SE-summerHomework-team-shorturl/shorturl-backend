package com.example.redirectservice.service;

import com.example.sharedentity.dao.ShortUrlDao;
import com.example.sharedentity.entity.ShortUrl;
import com.example.sharedentity.util.Base62Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional(rollbackFor = Exception.class)
public class RedirectServiceImpl implements RedirectService {
    @Autowired
    private ShortUrlDao shortUrlDao;
    @Autowired
    private ISendeService sendService;

    @Override
    public ShortUrl findShortUrlByToken(String token) throws Exception {
        long shortUrlId = Base62Encoder.decode(token);
        ShortUrl shortUrl = shortUrlDao.findById(shortUrlId);
        sendService.send().send(MessageBuilder
                .withPayload(shortUrlId)
                .build());
        return shortUrl;
    }
}
