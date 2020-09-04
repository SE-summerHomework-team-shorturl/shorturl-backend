package com.example.addurlservice.service;

import com.example.sharedentity.dao.ShortUrlDao;
import com.example.sharedentity.dto.Message;
import com.example.sharedentity.entity.ShortUrl;
import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class AddUrlServiceImpl implements AddUrlService {
    @Autowired
    private ShortUrlDao shortUrlDao;

    @Override
    public Message addToMyShortUrls(String url) {
        long userId = Long.parseLong((String) ((Jwt) SecurityContextHolder.getContext()
                .getAuthentication().getCredentials()).getClaims().get("user_name"));

        UrlValidator validator = new UrlValidator();
        if (!validator.isValid(url))
            return new Message(Message.Invalid_Url_Msg, null);

        ShortUrl shortUrl = new ShortUrl(url, userId);
        shortUrlDao.saveAndFlush(shortUrl);
        return new Message(Message.Success_Msg, shortUrl);
    }
}
