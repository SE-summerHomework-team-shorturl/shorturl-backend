package com.example.shorturl.service.impl;

import com.example.shorturl.dao.ShortUrlDao;
import com.example.shorturl.service.RedirectService;
import com.example.shorturl.util.Base62Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RedirectServiceImpl implements RedirectService {
    @Autowired
    private ShortUrlDao shortUrlDao;

    @Override
    public String findUrlByToken(String token) {
        Base62Encoder encoder = new Base62Encoder();
        int shortUrlId = encoder.decode(token);

        String url = shortUrlDao.findUrlByShortUrlId(shortUrlId);
        if (url == null)
            throw new RuntimeException("URL not found");

        return url;
    }
}
