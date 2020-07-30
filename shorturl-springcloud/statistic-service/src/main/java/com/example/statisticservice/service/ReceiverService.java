package com.example.statisticservice.service;



import com.example.sharedentity.dao.ShortUrlDao;
import com.example.sharedentity.dao.UrlStatisticDao;
import com.example.sharedentity.entity.ShortUrl;
import com.example.sharedentity.entity.UrlStatistic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(rollbackFor = Exception.class)
@EnableBinding(IReceiverService.class)
public class ReceiverService {
    @Autowired
    private UrlStatisticDao statisticDao;
    @StreamListener("dpb-exchange")
    public void onReceiver(Long shortUrlId){
        UrlStatistic urlStatistic=statisticDao.findById(shortUrlId);
        urlStatistic.setClicks(urlStatistic.getClicks()+1);
        statisticDao.save(urlStatistic);
    }
}
