package com.gateway.routes;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: YU
 * @Date: 16:04 2020/7/17
 * @Description: 网关 - 可以在这里进行配置 也可以使用application-test1.yml 里面的配置文件
 */
// @Configuration
public class RouteConfig {
    @Bean
    public RouteLocator appModuleAllocate(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r.path("/course/**").uri("http://localhost:8083/"))
                .route(r -> r.path("/mine/**").uri("http://localhost:8084/"))
                .build();
    }
}
