package com.example.userurlservice.service;

import com.example.sharedentity.dao.ShortUrlDao;
import com.example.sharedentity.dto.Message;
import com.example.sharedentity.entity.ShortUrl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class UserUrlServiceImpl implements UserUrlService {
    @Autowired private ShortUrlDao shortUrlDao;

    @Override
    public Message findAllMyShortUrls() {
        int userId = Integer.parseInt((String) ((Jwt) SecurityContextHolder.getContext()
                .getAuthentication().getCredentials()).getClaims().get("user_name"));
        List<ShortUrl> shortUrls = shortUrlDao.findAllByUserIdFetchStat(userId);
        return new Message(Message.Success_Msg, shortUrls);
    }

    @Override
    public Message deleteMyShortUrlById(long id) {
        int userId = Integer.parseInt((String) ((Jwt) SecurityContextHolder.getContext()
                .getAuthentication().getCredentials()).getClaims().get("user_name"));
        ShortUrl shortUrl = shortUrlDao.findById(id);
        if (shortUrl == null)
            return new Message(Message.No_URL_Msg, null);
        if (shortUrl.getUserId() != userId)
            return new Message(Message.Not_Your_URL_Msg, null);
        shortUrlDao.deleteById(id);
        return new Message(Message.Success_Msg, null);
    }
}
