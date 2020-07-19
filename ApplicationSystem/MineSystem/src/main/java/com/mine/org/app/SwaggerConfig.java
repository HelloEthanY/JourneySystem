package com.mine.org.app;

import com.github.xiaoymin.swaggerbootstrapui.annotations.EnableSwaggerBootstrapUI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Author: YU
 * @Date: 15:18 2019/12/16
 * @Description: Swagger 的配置类
 */
@Configuration
@EnableSwagger2 // 常用版 http://localhost:8082/researchSystem/swagger-ui.html
@EnableSwaggerBootstrapUI // 增强版 swagger http:localhost:8082/researchSystem/doc.html
public class SwaggerConfig {

    @Bean
    public Docket createApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.mine.org"))
                .paths(PathSelectors.any())
                .build();
    }

    // 创建api的基本信息
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Journey工作项目 接口文档")
                .description("更多技术内容分享见博客：http:localhost:8084/mine")
                .termsOfServiceUrl("https://blog.csdn.net/qq_24871519")
                .version("1.0")
                .licenseUrl("http:localhost:8084/mine")
                .build();
    }
}
