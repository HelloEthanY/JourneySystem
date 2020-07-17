package com.course.org;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class TestCourseApplication {
    public static void main(String[] args) {
        SpringApplication.run(TestCourseApplication.class, args);
    }
}
