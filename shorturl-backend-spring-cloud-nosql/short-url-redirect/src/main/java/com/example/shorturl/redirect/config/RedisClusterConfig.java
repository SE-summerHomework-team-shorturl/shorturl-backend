package com.example.shorturl.redirect.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

@Configuration
public class RedisClusterConfig {
    @Autowired
    private RedisClusterConfigProps props;

    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        System.out.println(props.getNodes());
        RedisClusterConfiguration config = new RedisClusterConfiguration(props.getNodes());
        JedisConnectionFactory factory = new JedisConnectionFactory(config);
        factory.afterPropertiesSet();
        return factory;
    }
}
