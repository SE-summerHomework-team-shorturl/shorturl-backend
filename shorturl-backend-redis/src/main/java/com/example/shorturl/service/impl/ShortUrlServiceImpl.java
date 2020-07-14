package com.example.shorturl.service.impl;

import com.example.shorturl.dao.ShortUrlDao;
import com.example.shorturl.dto.Message;
import com.example.shorturl.entity.ShortUrl;
import com.example.shorturl.entity.User;
import com.example.shorturl.misc.ShortUrlUserDetails;
import com.example.shorturl.service.ShortUrlService;
import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
public class ShortUrlServiceImpl implements ShortUrlService {
    @Autowired
    private ShortUrlDao shortUrlDao;

    @Override
    public Message addToMyShortUrls(String url) {
        User user = ((ShortUrlUserDetails) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal()).getUser();

        UrlValidator validator = new UrlValidator();
        if (!validator.isValid(url))
            return new Message("INVALID_URL", null);

        ShortUrl shortUrl = new ShortUrl();
        shortUrl.setUrl(url);
        shortUrl.setUserId(user.getId());

        shortUrl = shortUrlDao.add(shortUrl);

        return new Message("SUCCESS", shortUrl);
    }

    @Override
    public Message findAllMyShortUrls(int page, int size) {
        if (page < 0)
            throw new IllegalArgumentException("page should be non-negative");
        if (size <= 0)
            throw new IllegalArgumentException("size should be positive");

        User user = ((ShortUrlUserDetails) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal()).getUser();

        Set<ShortUrl> shortUrls = shortUrlDao
                .findAllByUserId(user.getId(), page, size);

        return new Message("SUCCESS", shortUrls);
    }

    @Override
    public Message deleteMyShortUrlById(int id) {
        User user = ((ShortUrlUserDetails) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal()).getUser();

        if (!shortUrlDao.delete(id, user.getId()))
            return new Message("NOT_YOUR_SHORT_URL", null);

        return new Message("SUCCESS", null);
    }
}
