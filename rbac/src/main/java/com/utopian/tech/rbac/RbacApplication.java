package com.utopian.tech.rbac;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling                                               // 开启定时任务
@MapperScan(basePackages = {"com.utopian.tech.rbac.mapper"})
@SpringBootApplication(scanBasePackages = "com.utopian.tech")   // scanBasePackages 扫描其他模块下的 bean
public class RbacApplication {
    public static void main(String[] args) {
        SpringApplication.run(RbacApplication.class, args);
    }
}
