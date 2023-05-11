package com.utopian.tech.base.annotation;


import com.utopian.tech.base.errorEnum.RequestErrorEnum;
import com.utopian.tech.base.exception.UtopianException;
import com.utopian.tech.base.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

@Aspect
@Component
@Slf4j
public class AvoidRepeatSubmitAspect {

    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 定义 @Pointcut注解表达式, 通过特定的规则来筛选连接点, 就是Pointcut，选中那几个你想要的方法
     * 在程序中主要体现为书写切入点表达式（通过通配、正则表达式）过滤出特定的一组 JointPoint连接点
     * 方式一：@annotation：当执行的方法上拥有指定的注解时生效（本博客采用这）
     * 方式二：execution：一般用于指定方法的执行
     */
    @Pointcut("@annotation(avoidRepeatSubmit)")
    public void pointCutNoRepeatSubmit(AvoidRepeatSubmit avoidRepeatSubmit) {

    }

    /**
     * 环绕通知, 围绕着方法执行
     * @return
     * @throws Throwable
     * @Around 可以用来在调用一个具体方法前和调用后来完成一些具体的任务。
     * <p>
     * 方式一：单用 @Around("execution(* net.wnn.controller.*.*(..))")可以
     * 方式二：用@Pointcut和@Around联合注解也可以（本博客采用这个）
     * <p>
     * <p>
     * 两种方式
     * 方式一：加锁 固定时间内不能重复提交
     * <p>
     * 方式二：先请求获取token，这边再删除token,删除成功则是第一次提交
     */
    @Around("pointCutNoRepeatSubmit(avoidRepeatSubmit)")
    public Object around(ProceedingJoinPoint joinPoint, AvoidRepeatSubmit avoidRepeatSubmit) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        //用于记录成功或者失败
        boolean res = false;
        //防重提交类型
        String type = avoidRepeatSubmit.limitType().name();
        if (type.equalsIgnoreCase(AvoidRepeatSubmit.Type.PARAM.name())) {
            //方式一，参数形式防重提交
        } else {
            //方式二，令牌形式防重提交
            String requestToken = request.getHeader("token");
            String userName = request.getHeader("userName");
//            String userName = getPostRequestBodyParameter(request, "userName");
            if (StringUtils.isBlank(requestToken)) {
                UtopianException utopianException = new UtopianException(RequestErrorEnum.REQUEST_LACK_PARAM_ERROR);
                throw utopianException;
            }
            String key = String.format(RedisUtil.SUBMIT_ORDER_TOKEN_KEY, userName, requestToken);
            /**
             * 提交表单的token key
             * 方式一：不用lua脚本获取再判断，之前是因为 key组成是 order:submit:accountNo, value是对应的token，所以需要先获取值，再判断
             * 方式二：可以直接key是 order:submit:accountNo:token,然后直接删除成功则完成
             */
            res = redisTemplate.delete(key);
        }
        if (!res) {
            log.error("请求重复提交");
            log.info("环绕通知中");
            return null;
        }
        log.info("环绕通知执行前");
        Object obj = joinPoint.proceed();
        log.info("环绕通知执行后");
        return obj;
    }

    public String getRequestBodyParameter(HttpServletRequest request, String paramName) {
        StringBuilder requestBody = new StringBuilder();
        try (BufferedReader reader = request.getReader()) {
            char[] charBuffer = new char[128];
            int bytesRead;
            while ((bytesRead = reader.read(charBuffer)) != -1) {
                requestBody.append(charBuffer, 0, bytesRead);
            }
        } catch (IOException e) {
            // handle exception
        }
        // 解析参数
        String[] params = requestBody.toString().split("&");
        for (String param : params) {
            String[] keyValue = param.split("=");
            if (keyValue.length == 2 && keyValue[0].equals(paramName)) {
                return keyValue[1];
            }
        }
        return null;
    }


//    public String getPostRequestBodyParameter(HttpServletRequest request, String paramName) throws IOException, JSONException {
//        String requestBody = null;
//
//        BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
//        StringBuilder builder = new StringBuilder();
//        String line = null;
//        while ((line = reader.readLine()) != null) {
//            builder.append(line);
//        }
//        requestBody = builder.toString();
//
//        // 解析JSON请求体
//        JSONObject jsonObject = new JSONObject(requestBody);
//        String paramValue = jsonObject.getString(paramName);
//
//        return paramValue;
//    }

    public String getPostRequestBodyParameter(HttpServletRequest request, String paramName) throws IOException, JSONException {
        String requestBody = null;
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = null;
        try {
            InputStream inputStream = request.getInputStream();
            if (inputStream != null) {
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                char[] charBuffer = new char[128];
                int bytesRead;
                while ((bytesRead = bufferedReader.read(charBuffer)) != -1) {
                    stringBuilder.append(charBuffer, 0, bytesRead);
                }
            }
        } catch (IOException ex) {
            throw ex;
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException ex) {
                    throw ex;
                }
            }
        }
        requestBody = stringBuilder.toString();

        // 解析JSON请求体
        JSONObject jsonObject = new JSONObject(requestBody);
        String paramValue = jsonObject.getString(paramName);

        return paramValue;
    }

}
