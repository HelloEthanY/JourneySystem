package com.gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.lang.reflect.Method;

/**
 * @Author: YU
 * @Date: 14:08 2020/7/10
 * @Description: 网关过滤器适配器
 */
@Component
public class GatewayRequesterFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        System.out.println("开始过滤拉？？？？？？？");
        String path = exchange.getRequest().getPath().toString();
        System.out.println("请求地址：：" + path);
        // if (!"/mine/login/appLogin".equals(path)) {
        /* 验证 token **/
        // String token = exchange.getRequest().getHeaders().getFirst("token");
        //  if (!"".equals(token)) {
        // 校验失败，响应未认证
        //   ServerHttpRequest httpRequest = exchange.getRequest().mutate().header("from", "token").build();
        // return chain.filter(exchange.mutate().request(httpRequest).build());
        //  }
        //  }
        ServerHttpRequest httpRequest = exchange.getRequest().mutate().header("from", "gateway").build();
        return chain.filter(exchange.mutate().request(httpRequest).build());
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
