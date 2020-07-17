package com.example.addurlservice.controller;

import com.example.addurlservice.service.AddUrlService;
import com.example.misc.UrlShortenerUserDetails;
import com.example.sharedentity.dto.Message;
import com.example.sharedentity.entity.ShortUrl;
import com.example.sharedentity.entity.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@AutoConfigureMockMvc
@SpringBootTest
@ActiveProfiles("test")
@ContextConfiguration
class AddUrlControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext wac; // 注入WebApplicationContext
    @MockBean
    private AddUrlService addUrlService;
    @BeforeEach
    void setUp() {}

    @AfterEach
    void tearDown() {
    }
    @Test
    @DisplayName("returnSucessWhenRightUrlUser")
    @WithUserDetails("test")
    void addToMyShortUrls() throws Exception {
        String testUrl="http://www.baidu.com";
        String testUserId="1";
        ShortUrl shortUrl=new ShortUrl(testUrl,1);
        shortUrl.setId(6);
        Message SuccessMessage=new Message("SUCCESS", shortUrl);
        when( addUrlService.addToMyShortUrls(testUrl)).thenReturn(SuccessMessage);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/urlmanage/addurl")
                .param("url",testUrl)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("status").value("SUCCESS"))
                .andDo(MockMvcResultHandlers.print())

                .andReturn();
    }
}
