package com.gateway.routes;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RouteConfig {
    @Bean
    public RouteLocator appModuleAllocate(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r.path("/course/**").uri("lb://JourneyTestCourse"))
                .route(r -> r.path("/work/**").uri("lb://WorkSystem"))
                .build();
    }

}
