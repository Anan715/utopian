package com.utopian.tech.base.util;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@Component
public class RedisUtil {

    @Autowired
    private StringRedisTemplate redisTemplate;

    private static final String ALL_CHAR_NUM = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    /**
     * 提交订单令牌的缓存key
     */
    public static final String SUBMIT_ORDER_TOKEN_KEY = "order:submit:%s";

    /**
     * 下单前获取令牌用于防重提交
     * @return
     */
    public String getAvoidSubmitToken(String userName){
        //随机获取32位的数字+字母作为token
        String token = RandomStringUtils.random(32, ALL_CHAR_NUM);
        //key的组成
        String key = String.format(SUBMIT_ORDER_TOKEN_KEY,userName);
        //令牌有效时间是30分钟
        redisTemplate.opsForValue().set(key, token,30, TimeUnit.SECONDS);
        return token;
    }

    /**
     * 获取随机长度的串
     *
     * @param length
     * @return
     */
    public static String getStringNumRandom(int length) {
        //生成随机数字和字母,
        Random random = new Random();
        StringBuilder saltString = new StringBuilder(length);
        for (int i = 1; i <= length; ++i) {

            saltString.append(ALL_CHAR_NUM.charAt(random.nextInt(ALL_CHAR_NUM.length())));
        }
        return saltString.toString();
    }


}
