package com.example.shorturl.service.impl;

import com.example.shorturl.dao.ShortUrlDao;
import com.example.shorturl.entity.ShortUrl;
import com.example.shorturl.service.RedirectService;
import com.example.shorturl.util.Base62Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RedirectServiceImpl implements RedirectService {
    @Autowired
    private ShortUrlDao shortUrlDao;

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public ShortUrl findShortUrlByToken(String token) throws Exception {
        Base62Encoder base62Encoder = new Base62Encoder();
        int shortUrlId = base62Encoder.decode(token);
        return shortUrlDao.findById(shortUrlId);
    }
}
