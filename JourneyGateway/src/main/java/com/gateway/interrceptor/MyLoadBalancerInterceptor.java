package com.gateway.interrceptor;

import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerRequestFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.Assert;

import java.io.IOException;
import java.net.URI;

public class MyLoadBalancerInterceptor implements ClientHttpRequestInterceptor {


    private LoadBalancerClient loadBalancer;
    private LoadBalancerRequestFactory requestFactory;

    public MyLoadBalancerInterceptor(LoadBalancerClient loadBalancer, LoadBalancerRequestFactory requestFactory) {
        this.loadBalancer = loadBalancer;
        this.requestFactory = requestFactory;
    }

    public MyLoadBalancerInterceptor(LoadBalancerClient loadBalancer) {
        this(loadBalancer, new LoadBalancerRequestFactory(loadBalancer));
    }


    @Override
    public ClientHttpResponse intercept(HttpRequest httpRequest, byte[] bytes, ClientHttpRequestExecution clientHttpRequestExecution) throws IOException {
        final URI originalUri = httpRequest.getURI();
        String serviceName = originalUri.getHost();
        System.out.println("进入自定义的请求拦截器中" + serviceName);
        Assert.state(serviceName != null, "Request URI does not contain a valid hostname: " + originalUri);
        return this.loadBalancer.execute(serviceName, requestFactory.createRequest(httpRequest, bytes, clientHttpRequestExecution));
    }
}
