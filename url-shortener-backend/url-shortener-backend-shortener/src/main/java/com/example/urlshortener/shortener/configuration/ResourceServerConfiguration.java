package com.example.urlshortener.shortener.configuration;

import com.example.urlshortener.shortener.misc.UrlShortenerRemoteTokenServices;
import com.example.urlshortener.shortener.misc.UrlShortenerUserAuthenticationConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

@Configuration
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {
    @Autowired
    private ResourceServerConfigurationProperties props;
    @Autowired
    private RedisConnectionFactory factory;

    @Bean
    public TokenStore tokenStore() {
        return new RedisTokenStore(factory);
    }

    @Bean
    @Primary
    public ResourceServerTokenServices resourceServerTokenServices() {
        UrlShortenerRemoteTokenServices services = new UrlShortenerRemoteTokenServices();
        services.setClientId(props.getClient().getClientId());
        services.setClientSecret(props.getClient().getClientSecret());
        services.setCheckTokenEndpointUrl(props.getAuthorization().getCheckTokenAccess());
        DefaultAccessTokenConverter accessTokenConverter = new DefaultAccessTokenConverter();
        accessTokenConverter.setUserTokenConverter(new UrlShortenerUserAuthenticationConverter());
        services.setAccessTokenConverter(accessTokenConverter);
        return services;
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.tokenServices(resourceServerTokenServices());
    }
}
