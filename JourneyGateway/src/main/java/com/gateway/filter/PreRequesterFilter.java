package com.gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @Author: YU
 * @Date: 14:08 2020/7/10
 * @Description: 网关过滤器适配器
 */
@Component
public class PreRequesterFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        System.out.println("开始过滤拉？？？？？？？");
        // http://localhost:8082/course/test/hello?name=hello
       /* HttpCookie password1 = exchange.getRequest().getBody().
        if (password1 != null) {
            String pa = password1.getName();
            System.out.println("namepa::::" + pa);
            System.out.println("value:::" + password1.getValue());
        }*/
        String name = exchange.getRequest().getQueryParams().getFirst("name");
        if (name == null) {
            System.out.println("用户名未空？？？？？");
            exchange.getResponse().setStatusCode(HttpStatus.NOT_ACCEPTABLE);
            return exchange.getResponse().setComplete();
        } else {
            System.out.println("name::::" + name);
        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
