package com.example.misc;

import com.example.sharedentity.entity.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import java.util.HashMap;
import java.util.HashSet;

@AutoConfigureMockMvc
@SpringBootTest
@ActiveProfiles("test")
public class AuthTokenTests {
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
        user.setAdmin(false);
        Authentication userAuth = new MyAuthentication(user);
        OAuth2Request request = new OAuth2Request(new HashMap<>(), "client", new HashSet<>(),
                true, new HashSet<>(), new HashSet<>(), "", new HashSet<>(), new HashMap<>());
        OAuth2Authentication oauth = new OAuth2Authentication(request, userAuth);
        TokenEnhancer enhancer = new UrlShortenerTokenEnhancer();
        token = enhancer.enhance(token, oauth);
        store.storeAccessToken(token, oauth);
    }

}
