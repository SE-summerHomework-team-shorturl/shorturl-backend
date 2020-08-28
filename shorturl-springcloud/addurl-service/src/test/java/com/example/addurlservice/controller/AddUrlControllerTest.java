package com.example.addurlservice.controller;

import com.example.addurlservice.service.AddUrlService;
import com.example.sharedentity.dto.Message;
import com.example.sharedentity.entity.ShortUrl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@AutoConfigureMockMvc
@SpringBootTest
@ContextConfiguration
class AddUrlControllerTest {
    @Autowired
    private AddUrlController addUrlController;
    @MockBean
    private AddUrlService addUrlService;

    @Test
    @DisplayName("returnSuccessWhenRightUrlUser")
    void addToMyShortUrls() throws Exception {
        String testInfo="test info";
        String testUrl = "test://url";
        Message testMsg = new Message(testInfo,"test 1");
        ShortUrl shortUrl=new ShortUrl(1L,testUrl,1L);
        when( addUrlService.addToMyShortUrls(testUrl)).thenReturn(testMsg);
        assertEquals(addUrlController.addToMyShortUrls(testUrl),testMsg);
    }
}
