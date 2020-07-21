package com.example.redirectservice.service;

import com.example.sharedentity.dao.ShortUrlDao;
import com.example.sharedentity.entity.ShortUrl;
import com.example.sharedentity.util.Base62Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class RedirectServiceImpl implements RedirectService {
    @Autowired private ShortUrlDao shortUrlDao;

    @Override
    public ShortUrl findShortUrlByToken(String token) throws Exception {
        int shortUrlId = Base62Encoder.decode(token);
        ShortUrl shortUrl = shortUrlDao.findById(shortUrlId);
        shortUrl.setClicks(shortUrl.getClicks()+1);
        shortUrlDao.saveAndFlush(shortUrl);
        return shortUrl;
    }
}