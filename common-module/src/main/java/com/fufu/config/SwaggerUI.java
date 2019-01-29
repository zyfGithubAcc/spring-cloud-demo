package com.fufu.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerUI {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.fufu.controller"))//指定扫描的包
                .paths(PathSelectors.any())
                .build();
    }
    private ApiInfo apiInfo() {
        //api文档信息
        return new ApiInfoBuilder()
                .title("swagger-ui构建api文档")
                .description("简单优雅的restful")
                .termsOfServiceUrl("待定")
                .version("1.0")
                .build();
    }
}
