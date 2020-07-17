package com.example.addurlservice.service;

import com.example.sharedentity.dao.ShortUrlDao;
import com.example.sharedentity.dto.Message;
import com.example.sharedentity.entity.ShortUrl;
import com.example.sharedentity.entity.User;
import com.example.sharedentity.util.Base62Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class AddUrlServiceImpl implements AddUrlService {
    @Autowired private ShortUrlDao shortUrlDao;

    @Override
<<<<<<< HEAD
    public Message addToMyShortUrls(String url,Integer id) {
        ShortUrl shortUrl = new ShortUrl(url, id);
        // TODO: 2020/7/16  need to check the shortUrl after return from saveAndFlush
=======
    public Message addToMyShortUrls(String url) {
        User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        ShortUrl shortUrl = new ShortUrl(url, user.getId());
>>>>>>> springcloud
        shortUrl = shortUrlDao.saveAndFlush(shortUrl);
        System.out.println( shortUrl);
        return new Message("SUCCESS", shortUrl);
}
}
