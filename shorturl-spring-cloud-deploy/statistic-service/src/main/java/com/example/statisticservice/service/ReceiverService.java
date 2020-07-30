package com.example.statisticservice.service;



import com.example.sharedentity.dao.ShortUrlDao;
import com.example.sharedentity.entity.ShortUrl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
@Transactional(rollbackFor = Exception.class)
@EnableBinding(IReceiverService.class)
public class ReceiverService {
    @Autowired
    private ShortUrlDao shortUrlDao;
    @StreamListener("dpb-exchange")
    public void onReceiver(List<Long> payloadList) {
        Map<Long, Integer> map = new HashMap<>();

        for (long shortUrlId : payloadList) {
            map.putIfAbsent(shortUrlId, 0);
            map.put(shortUrlId, map.get(shortUrlId) + 1);
        }

        for (Map.Entry<Long, Integer> entry : map.entrySet()) {
            long shortUrlId = entry.getKey();
            int clicks = entry.getValue();
            ShortUrl shortUrl = shortUrlDao.findById(shortUrlId);
            shortUrl.setClicks(shortUrl.getClicks() + clicks);
            shortUrlDao.save(shortUrl);
        }
    }
}
