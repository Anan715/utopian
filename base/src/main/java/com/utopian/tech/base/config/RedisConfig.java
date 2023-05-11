package com.utopian.tech.base.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;

@Configuration
public class RedisConfig {
    private final RedisConnectionFactory redisConnectionFactory;

    public RedisConfig(RedisConnectionFactory redisConnectionFactory) {
        this.redisConnectionFactory = redisConnectionFactory;
    }

    @Primary
    @Bean(name = "stringRedisTemplate")
    public StringRedisTemplate stringRedisTemplate() {
        StringRedisTemplate template = new StringRedisTemplate();
        template.setConnectionFactory(redisConnectionFactory);
        return template;
    }


    @Bean(name = "stringRedisTemplateTransaction")
    public StringRedisTemplate stringRedisTemplateTransaction() {
        StringRedisTemplate template = new StringRedisTemplate();
        template.setEnableTransactionSupport(true);
        template.setConnectionFactory(redisConnectionFactory);
        return template;
    }


}
