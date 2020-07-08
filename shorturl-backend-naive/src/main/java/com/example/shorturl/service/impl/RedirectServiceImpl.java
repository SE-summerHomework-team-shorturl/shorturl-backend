package com.example.shorturl.service.impl;

import com.example.shorturl.dao.ShorturlDao;
import com.example.shorturl.entity.Shorturl;
import com.example.shorturl.service.RedirectService;
import com.example.shorturl.util.Base62Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class RedirectServiceImpl implements RedirectService {
    @Autowired private ShorturlDao shorturlDao;

    @Override
    public Shorturl findShorturlByToken(String token) throws Exception {
        int shorturlId = Base62Encoder.decode(token);
        Shorturl shorturl = shorturlDao.findById(shorturlId);
        if (shorturl == null)
            throw new Exception("Url not found");
        return shorturl;
    }
}
