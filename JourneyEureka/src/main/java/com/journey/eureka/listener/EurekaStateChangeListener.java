package com.journey.eureka.listener;

import com.netflix.discovery.shared.Applications;
import com.netflix.eureka.EurekaServerContextHolder;
import com.netflix.eureka.registry.PeerAwareInstanceRegistry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.eureka.server.event.EurekaInstanceCanceledEvent;
import org.springframework.cloud.netflix.eureka.server.event.EurekaInstanceRegisteredEvent;
import org.springframework.cloud.netflix.eureka.server.event.EurekaInstanceRenewedEvent;
import org.springframework.cloud.netflix.eureka.server.event.EurekaRegistryAvailableEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: YU
 * @Date: 11:25 2020/7/17
 * @Description: 服务上下线监控
 */
@Slf4j
@Configuration
@SuppressWarnings("all")
public class EurekaStateChangeListener implements ApplicationListener {

    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        // 服务器挂掉事件
        if (applicationEvent instanceof EurekaInstanceCanceledEvent) {
            EurekaInstanceCanceledEvent event = (EurekaInstanceCanceledEvent) applicationEvent;
            // 获取当前节点Eureka 实例中的节点信息
            PeerAwareInstanceRegistry registry = EurekaServerContextHolder.getInstance().getServerContext().getRegistry();
            Applications applications = registry.getApplications();
            applications.getRegisteredApplications().forEach((registeredApplication) -> {
                registeredApplication.getInstances().forEach((instance) -> {
                    if (instance.getInstanceId().equals(event.getServerId())) {
                        log.error("服务：" + instance.getAppName() + "挂拉！");
                    }
                });
            });
        }
        if (applicationEvent instanceof EurekaInstanceRegisteredEvent) {
            EurekaInstanceRegisteredEvent registeredEvent = (EurekaInstanceRegisteredEvent) applicationEvent;
            log.trace("服务：" + registeredEvent.getInstanceInfo().getAppName() + "注册成功了！");
        }
        if (applicationEvent instanceof EurekaInstanceRenewedEvent) {
            EurekaInstanceRenewedEvent renewedEvent = (EurekaInstanceRenewedEvent) applicationEvent;
            log.trace("服务：" + renewedEvent.getInstanceInfo().getAppName() + "。。。");
        }
        if (applicationEvent instanceof EurekaRegistryAvailableEvent) {
            log.trace("服务 Aualiable...");
        }
    }
}
