package com.example.shorturl.service.impl;

import com.example.shorturl.dao.ShortUrlDao;
import com.example.shorturl.entity.ShortUrl;
import com.example.shorturl.service.RedirectService;
import com.example.shorturl.util.Base62Encoder;
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
        if (shortUrl == null)
            throw new Exception("Short url not found");
        return shortUrl;
    }
}
