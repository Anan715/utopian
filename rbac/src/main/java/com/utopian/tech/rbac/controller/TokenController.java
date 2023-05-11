package com.utopian.tech.rbac.controller;

import com.utopian.tech.base.exception.UtopianException;
import com.utopian.tech.base.response.UtopianResponse;
import com.utopian.tech.base.util.RedisUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class TokenController {

    @Resource
    private RedisUtil redisUtil;

    @GetMapping("/avoid/token")
    public UtopianResponse<String> getToken(String userName) throws UtopianException {
        return UtopianResponse.successWithData(redisUtil.getAvoidSubmitToken(userName));
    }
}
