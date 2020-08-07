package com.example.statisticservice.service;


import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.SubscribableChannel;

public interface IReceiverService {
    @Input("dpb-exchange")
    SubscribableChannel receiver();
}
