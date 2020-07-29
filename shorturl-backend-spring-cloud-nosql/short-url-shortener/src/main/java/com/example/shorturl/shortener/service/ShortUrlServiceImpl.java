package com.example.shorturl.shortener.service;

import com.example.shorturl.shortener.dao.ShortUrlDao;
import com.example.shorturl.shortener.entity.ShortUrl;
import com.example.shorturl.shortener.misc.DataCenterFactory;
import com.example.shorturl.util.dto.Message;
import com.example.shorturl.util.misc.DataCenter;
import com.example.shorturl.util.misc.IdWorker;
import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

@Service
public class ShortUrlServiceImpl implements ShortUrlService {
    @Autowired
    private ShortUrlDao shortUrlDao;
    @Autowired
    private DataCenterFactory dataCenterFactory;

    @Override
    public Message addToMyShortUrls(String url) {
        long userId = Long.parseLong((String) ((Jwt) SecurityContextHolder.getContext()
                .getAuthentication().getCredentials()).getClaims().get("user_name"));

        UrlValidator urlValidator = new UrlValidator();
        if (!urlValidator.isValid(url))
            return new Message("INVALID_URL", null);

        DataCenter dataCenter = dataCenterFactory.getInstance();
        IdWorker idWorker = dataCenter.pickAnIdWorker();
        long shortUrlId = idWorker.nextId();

        ShortUrl shortUrl = new ShortUrl();
        shortUrl.setId(shortUrlId);
        shortUrl.setUrl(url);
        shortUrl.setUserId(userId);
        shortUrl = shortUrlDao.save(shortUrl);

        return new Message("SUCCESS", shortUrl);
    }
}
