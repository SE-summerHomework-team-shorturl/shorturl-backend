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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
@Transactional(rollbackFor = Exception.class)
@EnableBinding(IReceiverService.class)
public class ReceiverService {
    @Autowired
    private UrlStatisticDao statisticDao;
    @StreamListener("dpb-exchange")
    public void onReceiver(List<Long> payloadlist){
        HashMap<Long,Integer> map = new HashMap<Long,Integer>();
        int size = payloadlist.size();
        for(int i=0;i<size;i++)
        {
            Long key = payloadlist.get(i);
            if(map.containsKey(key))
                map.put(key,map.get(key)+1);
            else
                map.put(key,1);
        }
        List<ShortUrl> shortUrls = new ArrayList<>();
        for(Map.Entry<Long,Integer> entry : map.entrySet())
        {
            Long key = entry.getKey();
            Integer value = entry.getValue();
            ShortUrl shortUrl = shortUrlDao.findById(key);
            shortUrl.setClicks(value+shortUrl.getClicks());
            shortUrls.add(shortUrl);
        }
        shortUrlDao.saveAll(shortUrls);
    public void onReceiver(Long shortUrlId){
        UrlStatistic urlStatistic=statisticDao.findById(shortUrlId);
        urlStatistic.setClicks(urlStatistic.getClicks()+1);
        statisticDao.save(urlStatistic);
    }
}
