package com.example.addurlservice.controller;

import com.example.addurlservice.AddurlserviceApplication;
import com.example.addurlservice.service.AddUrlService;

import com.example.misc.*;
import com.example.sharedentity.dto.Message;
import com.example.sharedentity.entity.ShortUrl;
import com.example.sharedentity.entity.User;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.context.WebApplicationContext;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.HashSet;

import static org.mockito.Mockito.when;

@AutoConfigureMockMvc
@SpringBootTest
@ActiveProfiles("test")
@ContextConfiguration
class AddUrlControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext wac;
    @MockBean
    private AddUrlService addUrlService;

    static public String test_token = "fake-token";
/*
    @BeforeAll
    static public void beforeAll() {
        RedisStandaloneConfiguration config = new RedisStandaloneConfiguration("localhost", 6379);
        JedisConnectionFactory factory = new JedisConnectionFactory(config);
        StringRedisTemplate template = new StringRedisTemplate(factory);
        for (String key : template.keys("*"))
            template.delete(key);
        RedisTokenStore store = new RedisTokenStore(factory);
        OAuth2AccessToken token = new DefaultOAuth2AccessToken(test_token);
        User user = new User();
        user.setId((int) 1L);
        user.setUsername("bill");
        user.setPassword("1234");
        user.setEmail("bill@example.com");
        user.setAdmin(false);
        Authentication userAuth = new MyAuthentication(user);
        OAuth2Request request = new OAuth2Request(new HashMap<>(), "client", new HashSet<>(),
                true, new HashSet<>(), new HashSet<>(), "", new HashSet<>(), new HashMap<>());
        OAuth2Authentication oauth = new OAuth2Authentication(request, userAuth);
        TokenEnhancer enhancer = new UrlShortenerTokenEnhancer();
        token = enhancer.enhance(token, oauth);
        store.storeAccessToken(token, oauth);
    }
    @BeforeEach
    void setUp() {}

    @AfterEach
    void tearDown() {
    }
    @Test
    @DisplayName("returnSucessWhenRightUrlUser")

    void addToMyShortUrls() throws Exception {
        String testUrl="http://www.baidu.com";
        String testUserId="1";
        ShortUrl shortUrl=new ShortUrl(testUrl,1);
        shortUrl.setId(6);
        Message SuccessMessage=new Message("SUCCESS", shortUrl);
        when( addUrlService.addToMyShortUrls(testUrl)).thenReturn(SuccessMessage);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/urlmanage/addurl")
                .param("url",testUrl)
                .header("Authorization", "Bearer " +test_token)
                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("status").value("SUCCESS"))
                .andDo(MockMvcResultHandlers.print())

                .andReturn();
    }*/
}
