package com.example.addurlservice.service;

import com.example.addurlservice.dao.ShortUrlDao;
import com.example.sharedentity.dto.Message;
import com.example.sharedentity.entity.ShortUrl;
import com.example.sharedentity.entity.User;
import com.example.sharedentity.util.Base62Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class AddUrlServiceImpl implements AddUrlService {
    @Autowired private ShortUrlDao shortUrlDao;

    @Override
    public Message addToMyShortUrls(String url) {
        User user = ((ShortUrlUserDetails) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal()).getUser();
        UrlValidator validator = new UrlValidator();
        if (!validator.isValid(url))
            return new Message("INVALID_URL", null);
        ShortUrl shortUrl = new ShortUrl(url, user.getId());
        shortUrl = shortUrlDao.saveAndFlush(shortUrl);
        return new Message("SUCCESS", shortUrl);
    }
}
