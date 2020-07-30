package com.example.redirectservice.service;

import com.example.sharedentity.dao.ShortUrlDao;
import com.example.sharedentity.entity.ShortUrl;
import com.example.sharedentity.util.Base62Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class RedirectServiceImpl implements RedirectService {
    @Autowired
    private ShortUrlDao shortUrlDao;
    @Autowired
    private ISendeService sendService;

    static private ThreadLocal<List<Long>> threadStorage;

    @Override
    public ShortUrl findShortUrlByToken(String token) throws Exception {
        if (threadStorage == null)
            threadStorage = new ThreadLocal<>();
        List<Long> payloadList = threadStorage.get();
        if (payloadList == null) {
            payloadList = new ArrayList<>();
            threadStorage.set(payloadList);
        }

        long shortUrlId = Base62Encoder.decode(token);
        ShortUrl shortUrl = shortUrlDao.findById(shortUrlId);

        payloadList.add(shortUrlId);
        if (payloadList.size() >= 100) {
            sendService.send().send(MessageBuilder.withPayload(payloadList).build());
            payloadList.clear();
        }

        return shortUrl;
    }
}
