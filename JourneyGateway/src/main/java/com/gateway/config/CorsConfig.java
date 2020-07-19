package com.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * @Author: YU
 * @Date: 19:26 2019/12/14
 * @Description: 跨域配置
 */
//@Configuration
public class CorsConfig {

    // 设置允许跨域的源
    private static String[] originsVal = new String[]{
            "192.168.16.115:8082",
            "localhost:8082",
    };

    /**
     * 跨域过滤器
     *
     * @return
     */
    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        // this.addAllowedOrigins(corsConfiguration);
        corsConfiguration.addAllowedHeader("*"); // 2允许任何头
        corsConfiguration.addAllowedMethod("*"); // 3允许任何方法（post、get等）
        corsConfiguration.addAllowedOrigin("*"); // 1允许任何域名使用
        corsConfiguration.setAllowCredentials(true);
        source.registerCorsConfiguration("/**", corsConfiguration);
        return new CorsFilter(source);
    }

    private void addAllowedOrigins(CorsConfiguration corsConfiguration) {
        for (String origin : originsVal) {
            corsConfiguration.addAllowedOrigin("http://" + origin);
            corsConfiguration.addAllowedOrigin("https://" + origin);
        }
    }
}
