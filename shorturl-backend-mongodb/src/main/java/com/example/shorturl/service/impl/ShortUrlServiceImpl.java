package com.example.shorturl.service.impl;

import com.example.shorturl.dao.CounterDao;
import com.example.shorturl.dao.ShortUrlDao;
import com.example.shorturl.dto.Message;
import com.example.shorturl.entity.Counter;
import com.example.shorturl.entity.ShortUrl;
import com.example.shorturl.entity.User;
import com.example.shorturl.misc.ShortUrlUserDetails;
import com.example.shorturl.service.ShortUrlService;
import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ShortUrlServiceImpl implements ShortUrlService {
    @Autowired private ShortUrlDao shortUrlDao;
    @Autowired private CounterDao counterDao;

    @Override
    public Message addToMyShortUrls(String url) {
        User user = ((ShortUrlUserDetails) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal()).getUser();
        UrlValidator validator = new UrlValidator();
        if (!validator.isValid(url))
            return new Message("INVALID_URL", null);
        Query query = new Query(Criteria.where("_id").is(Counter.CounterId.SHORT_URL_ID.ordinal()));
        Update update = new Update().inc("seq", 1);
        Counter counter = counterDao.findAndModify(query, update);
        ShortUrl shortUrl = new ShortUrl(counter.getSeq(), url, user.getId());
        shortUrl = shortUrlDao.save(shortUrl);
        return new Message("SUCCESS", shortUrl);
    }

    @Override
    public Message findAllMyShortUrls(int page, int size) {
        User user = ((ShortUrlUserDetails) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal()).getUser();
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Order.desc("id")));
        List<ShortUrl> shortUrls = shortUrlDao.findAllByUserId(user.getId(), pageable).toList();
        return new Message("SUCCESS", shortUrls);
    }

    @Override
    public Message deleteMyShortUrlById(int id) {
        User user = ((ShortUrlUserDetails) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal()).getUser();
        ShortUrl shortUrl = shortUrlDao.findById(id);
        if (shortUrl == null)
            return new Message("SUCCESS", null);
        if (!shortUrl.getUserId().equals(user.getId()))
            return new Message("NOT_YOUR_SHORT_URL", null);
        shortUrlDao.deleteById(id);
        return new Message("SUCCESS", null);
    }
}
