package com.example.adminservice.configuration;


import com.example.misc.UrlShortenerRemoteTokenServices;
import com.example.misc.UrlShortenerUserAuthenticationConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;

@Configuration
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {
    @Autowired
    private ResourceServerConfigurationProperties props;
    /*
    @Autowired
    private RedisConnectionFactory factory;

    @Bean
    public TokenStore tokenStore() {
        return new RedisTokenStore(factory);
    }*/
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

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/actuator/**").permitAll();
    }
}
