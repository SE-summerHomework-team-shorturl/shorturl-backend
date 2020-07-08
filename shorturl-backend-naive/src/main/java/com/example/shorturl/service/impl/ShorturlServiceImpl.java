package com.example.shorturl.service.impl;

import com.example.shorturl.dao.ShorturlDao;
import com.example.shorturl.dto.Message;
import com.example.shorturl.entity.Shorturl;
import com.example.shorturl.entity.User;
import com.example.shorturl.service.ShorturlService;
import com.example.shorturl.util.Base62Encoder;
import misc.ShorturlUserDetails;
import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class ShorturlServiceImpl implements ShorturlService {
    @Autowired private ShorturlDao shorturlDao;

    @Override
    public Message addToMyShorturls(String url) {
        User user = ((ShorturlUserDetails) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal()).getUser();
        UrlValidator validator = new UrlValidator();
        if (!validator.isValid(url))
            return new Message("INVALID_URL", null);
        Shorturl shorturl = new Shorturl(url, user.getId());
        shorturl = shorturlDao.saveAndFlush(shorturl);
        return new Message("SUCCESS", shorturl);
    }

    @Override
    public Message findAllMyShorturls() {
        User user = ((ShorturlUserDetails) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal()).getUser();
        List<Shorturl> shorturls = shorturlDao.findAllByUserId(user.getId());
        return new Message("SUCCESS", shorturls);
    }

    @Override
    public Message deleteMyShorturlById(int id) {
        User user = ((ShorturlUserDetails) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal()).getUser();
        Shorturl shorturl = shorturlDao.findById(id);
        if (shorturl == null)
            return new Message("SUCCESS", null);
        if (shorturl.getUserId() != user.getId())
            return new Message("NOT_YOUR_SHORTURL", null);
        shorturlDao.deleteById(id);
        return new Message("SUCCESS", null);
    }
}
