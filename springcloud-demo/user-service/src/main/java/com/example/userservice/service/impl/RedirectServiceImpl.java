package com.example.userservice.service.impl;


import com.example.sharedentity.entity.Shorturl;
import com.example.sharedentity.util.Base62Encoder;
import com.example.userservice.dao.ShorturlDao;
import com.example.userservice.service.RedirectService;
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
