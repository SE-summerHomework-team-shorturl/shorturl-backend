package com.example.userurlservice.service;

import com.example.misc.UrlShortenerUserDetails;
import com.example.sharedentity.dao.ShortUrlDao;
import com.example.sharedentity.dto.Message;
import com.example.sharedentity.entity.ShortUrl;
import com.example.sharedentity.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class UserUrlServiceImpl implements UserUrlService {
    @Autowired private ShortUrlDao shortUrlDao;

    @Override
    public Message findAllMyShortUrls(int page, int size) {
        Integer userId = ((UrlShortenerUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser().getId();
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Order.desc("id")));
        List<ShortUrl> shortUrls = shortUrlDao.findAllByUserId(userId, pageable);
        return new Message("SUCCESS", shortUrls);
    }

    @Override
    public Message deleteMyShortUrlById(int id) {
        Integer userId = ((UrlShortenerUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser().getId();
        ShortUrl shortUrl = shortUrlDao.findById(id);
        if (shortUrl == null)
            return new Message("NO_SUCH_URL", null);
        if (!shortUrl.getUserId().equals(userId))
            return new Message("NOT_YOUR_SHORT_URL", null);
        shortUrlDao.deleteById(id);
        return new Message("SUCCESS", null);
    }
}
