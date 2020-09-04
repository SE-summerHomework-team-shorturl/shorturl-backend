package com.example.redirectservice.service;

import com.example.sharedentity.dao.ShortUrlDao;
import com.example.sharedentity.entity.ShortUrl;
import com.example.sharedentity.util.Base62Encoder;
import com.example.sharedentity.util.RedirectRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@Transactional(rollbackFor = Exception.class)
public class RedirectServiceImpl implements RedirectService {
    @Autowired
    private ShortUrlDao shortUrlDao;
    @Autowired
    private ISendeService sendService;

    @Override
    public ShortUrl findShortUrlByToken(String token) {
        long shortUrlId = Base62Encoder.decode(token);
        ShortUrl shortUrl = shortUrlDao.findById(shortUrlId);
        if (shortUrl != null) {
            RedirectRecord redirectRecord = new RedirectRecord(shortUrlId, LocalDate.now());
            sendService.send().send(MessageBuilder.withPayload(redirectRecord).build());
        }
        return shortUrl;
    }
}
