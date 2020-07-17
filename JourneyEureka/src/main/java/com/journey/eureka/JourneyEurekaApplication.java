package com.journey.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
/**
  *@Author: YU
  *@Date: 11:31 2020/7/17
  *@Description: 服务与注册 ： http://127.0.0.1:8000/
  */
@EnableEurekaServer
@SpringBootApplication
public class JourneyEurekaApplication {
    public static void main(String[] args) {
        SpringApplication.run(JourneyEurekaApplication.class, args);
    }
}
