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


    /**
     * 开启 redis事务后，在  @Transactional 执行的命令也会被认为在redis事务中执行的命令，此时 redis 在设置某值时，返回为 null，
     * 因此需要单独为其配置一个 stringRedisTemplateTransaction ,执行 redis 事务命令时使用该 bean
     * redis 执行非事务代码时，使用 stringRedisTemplate
     */
    @Bean(name = "stringRedisTemplateTransaction")
    public StringRedisTemplate stringRedisTemplateTransaction() {
        StringRedisTemplate template = new StringRedisTemplate();
        template.setEnableTransactionSupport(true);
        template.setConnectionFactory(redisConnectionFactory);
        return template;
    }


}
