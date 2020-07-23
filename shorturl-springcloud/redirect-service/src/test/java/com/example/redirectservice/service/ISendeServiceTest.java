package com.example.redirectservice.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.messaging.Message;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ISendeServiceTest {

    @Autowired
    private ISendeService sendService;

    @Test
    public void testStream(){
        String msg = "hello stream ...";
        // 将需要发送的消息封装为Message对象
        Message message = MessageBuilder
                .withPayload(msg.getBytes())
                .build();
        sendService.send().send(message );
    }
}
