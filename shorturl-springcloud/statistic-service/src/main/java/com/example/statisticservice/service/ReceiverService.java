package com.example.statisticservice.service;



import com.example.sharedentity.dao.ShortUrlDao;
import com.example.sharedentity.entity.ShortUrl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(rollbackFor = Exception.class)
@EnableBinding(IReceiverService.class)
public class ReceiverService {
    @Autowired
    private ShortUrlDao shortUrlDao;
    @StreamListener("dpb-exchange")
    public void onReceiver(Integer shortUrlId){
        System.out.println("消费者:"+ shortUrlId);
        ShortUrl shortUrl = shortUrlDao.findById(shortUrlId);
        shortUrl.setClicks(shortUrl.getClicks()+1);
        shortUrlDao.saveAndFlush(shortUrl);

    }
}
