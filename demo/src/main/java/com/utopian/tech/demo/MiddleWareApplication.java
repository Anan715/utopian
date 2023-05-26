package com.utopian.tech.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.utopian.tech.demo")
public class MiddleWareApplication {
    public static void main(String[] args) {
        SpringApplication.run(MiddleWareApplication.class, args);
    }
}
