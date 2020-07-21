package com.example.adminservice.controller;

import com.example.adminservice.service.AdminService;
import com.example.misc.MyAuthentication;
import com.example.misc.UrlShortenerTokenEnhancer;
import com.example.sharedentity.dto.Message;
import com.example.sharedentity.entity.User;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.HashSet;

import static org.mockito.Mockito.when;

@AutoConfigureMockMvc
@SpringBootTest
@ActiveProfiles("test")
@Transactional
class AdminControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private AdminService adminService;
    static public String test_token = "fake-token";
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
        user.setAdmin(true);
        Authentication userAuth = new MyAuthentication(user);
        OAuth2Request request = new OAuth2Request(new HashMap<>(), "client", new HashSet<>(),
                true, new HashSet<>(), new HashSet<>(), "", new HashSet<>(), new HashMap<>());
        OAuth2Authentication oauth = new OAuth2Authentication(request, userAuth);
        TokenEnhancer enhancer = new UrlShortenerTokenEnhancer();
        token = enhancer.enhance(token, oauth);
        store.storeAccessToken(token, oauth);
    }
    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @DisplayName("shouldSuccessWhenRightInput")
    void findAllShortUrls() throws Exception {
        String testInfo="test info";
        when( adminService.findAllShortUrls()).thenReturn(new Message(testInfo,null));
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/admin/findurl")
                .header("Authorization", "Bearer " + test_token)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("status").value(testInfo))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    @DisplayName("shouldSuccessWhenRightInput")
    void findAllUsers() throws Exception {
        String testInfo="test info";
        when( adminService.findAllUsers()).thenReturn(new Message(testInfo,null));
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/admin/finduser")
                .header("Authorization", "Bearer " + test_token)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("status").value(testInfo))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    @DisplayName("shouldSuccessWhenRightInput")
    void deleteShortUrlById() throws Exception {
        int id=1;
        String testInfo="test info";
        when(adminService.deleteShortUrlById(id)).thenReturn(new Message(testInfo,null));
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/admin/deleteurl")
                .param("id",String.valueOf(id))
                .header("Authorization", "Bearer " + test_token)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("status").value(testInfo))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }
}
