package com.example.statisticservice.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@EnableBinding(IReceiverService.class)
public class ReceiverService {

    @StreamListener("dpb-exchange")
    public void onReceiver(byte[] msg){
        System.out.println("消费者:"+new String(msg));
    }
}
