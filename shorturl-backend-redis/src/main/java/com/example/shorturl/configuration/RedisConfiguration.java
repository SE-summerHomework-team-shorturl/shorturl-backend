package com.example.shorturl.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

@Configuration
public class RedisConfiguration {
    @Autowired
    private RedisClusterConfigurationProperties props;

    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        RedisClusterConfiguration config =
                new RedisClusterConfiguration(props.getNodes());
        JedisConnectionFactory factory = new JedisConnectionFactory(config);
        factory.afterPropertiesSet();
        return factory;
    }
}
