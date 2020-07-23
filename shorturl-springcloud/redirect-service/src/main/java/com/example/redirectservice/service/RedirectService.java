package com.example.redirectservice.service;

import com.example.sharedentity.entity.ShortUrl;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.SubscribableChannel;

public interface RedirectService {
    ShortUrl findShortUrlByToken(String token) throws Exception;
    @Output("dpb-exchange")
    SubscribableChannel send();
}
