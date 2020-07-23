package com.example.redirectservice.service;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.stereotype.Service;

@Service
public interface ISendeService {
    @Output("dpb-exchange")
    SubscribableChannel output();
}
