package com.example.shorturl.manager.service;

import com.example.shorturl.manager.dao.ShortUrlDao;
import com.example.shorturl.manager.entity.ShortUrl;
import com.example.shorturl.util.dto.Message;
import com.example.shorturl.util.dto.SimplePage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

@Service
public class UserUrlServiceImpl implements UserUrlService {
    @Autowired
    private ShortUrlDao shortUrlDao;

    @Override
    public Message findAllMyShortUrls(int page, int size) {
        int userId = Integer.parseInt((String) ((Jwt) SecurityContextHolder.getContext()
                .getAuthentication().getCredentials()).getClaims().get("user_name"));

        SimplePage<ShortUrl> shortUrls = shortUrlDao.findAllByUserId(userId, page, size);

        return new Message("SUCCESS", shortUrls);
    }

    @Override
    public Message deleteMyShortUrlById(int id) {
        int userId = Integer.parseInt((String) ((Jwt) SecurityContextHolder.getContext()
                .getAuthentication().getCredentials()).getClaims().get("user_name"));

        shortUrlDao.delete(id, userId);

        return new Message("SUCCESS", null);
    }
}
