package com.example.shorturl.redirect.service;

import com.example.shorturl.redirect.dao.ShortUrlDao;
import com.example.shorturl.util.misc.Base62Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RedirectServiceImpl implements RedirectService {
    @Autowired
    private ShortUrlDao shortUrlDao;

    @Override
    public String findUrlByToken(String token) {
        long shortUrlId;
        Base62Encoder base62Encoder = new Base62Encoder();
        try {
            shortUrlId = base62Encoder.decode(token);
        } catch (Exception e) {
            return null;
        }

        return shortUrlDao.findUrlByShortUrlId(shortUrlId);
    }
}
