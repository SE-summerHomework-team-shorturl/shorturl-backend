package com.example.shorturl.shortener.service;

import com.example.shorturl.shortener.dao.ShortUrlDao;
import com.example.shorturl.shortener.entity.ShortUrl;
import com.example.shorturl.util.dto.Message;
import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

@Service
public class ShortUrlServiceImpl implements ShortUrlService {
    @Autowired
    private ShortUrlDao shortUrlDao;

    @Override
    public Message addToMyShortUrls(String url) {
        int userId = Integer.parseInt((String) ((Jwt) SecurityContextHolder.getContext()
                .getAuthentication().getCredentials()).getClaims().get("user_name"));

        UrlValidator urlValidator = new UrlValidator();
        if (!urlValidator.isValid(url))
            return new Message("INVALID_URL", null);

        ShortUrl shortUrl = new ShortUrl();
        shortUrl.setUrl(url);
        shortUrl.setUserId(userId);
        shortUrl = shortUrlDao.add(shortUrl);

        return new Message("SUCCESS", shortUrl);
    }
}
