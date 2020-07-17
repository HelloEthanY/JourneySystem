package com.journey.eureka.listener;

import com.netflix.appinfo.InstanceInfo;
import org.springframework.cloud.netflix.eureka.server.event.*;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @Author: YU
 * @Date: 11:25 2020/7/17
 * @Description: 服务上下线监控
 */
@Component
public class EurekaStateChangeListener {

    @EventListener
    public void listen(EurekaInstanceCanceledEvent event) { // 服务下线事件。
        System.err.println(event.getServerId() + "\t" + event.getAppName() + " 服务下线 ");
    }

    @EventListener
    public void listen(EurekaInstanceRegisteredEvent event) { // 服务注册
        InstanceInfo instanceInfo = event.getInstanceInfo();
        System.err.println(instanceInfo.getAppName() + " 进行注册 ");
    }

    @EventListener
    public void listen(EurekaInstanceRenewedEvent event) {
        System.err.println(event.getServerId() + "\t" + event.getAppName() + " 服务进行续约 ");
    }

    @EventListener
    public void listen(EurekaRegistryAvailableEvent event) {
        System.err.println(" 注册中心启动 ");
    }

    @EventListener
    public void listen(EurekaServerStartedEvent event) {
        System.err.println("Eureka Server启动 ");
    }
}
