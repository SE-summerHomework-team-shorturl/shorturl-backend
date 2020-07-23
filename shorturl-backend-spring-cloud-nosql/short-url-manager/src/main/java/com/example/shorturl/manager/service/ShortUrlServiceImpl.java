package com.example.shorturl.manager.service;

import com.example.shorturl.manager.dao.ShortUrlDao;
import com.example.shorturl.manager.entity.ShortUrl;
import com.example.shorturl.util.dto.Message;
import com.example.shorturl.util.dto.SimplePage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

@Service
public class ShortUrlServiceImpl implements ShortUrlService {
    @Autowired
    private ShortUrlDao shortUrlDao;

    @Override
    public Message findAllMyShortUrls(int page, int size) {
        long userId = Long.parseLong((String) ((Jwt) SecurityContextHolder.getContext()
                .getAuthentication().getCredentials()).getClaims().get("user_name"));

        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Order.asc("id")));
        Page<ShortUrl> shortUrlPage = shortUrlDao.findAllByUserId(userId, pageable);

        SimplePage<ShortUrl> shortUrlSimplePage = new SimplePage<>(
                page, size,shortUrlPage.getTotalElements(), shortUrlPage.getContent());

        return new Message("SUCCESS", shortUrlSimplePage);
    }

    @Override
    public Message deleteMyShortUrlById(long id) {
        long userId = Long.parseLong((String) ((Jwt) SecurityContextHolder.getContext()
                .getAuthentication().getCredentials()).getClaims().get("user_name"));

        shortUrlDao.deleteByIdAndUserId(id, userId);

        return new Message("SUCCESS", null);
    }
}
