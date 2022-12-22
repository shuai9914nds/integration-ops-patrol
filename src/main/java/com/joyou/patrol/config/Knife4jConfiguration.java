package com.joyou.patrol.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
@EnableOpenApi
public class Knife4jConfiguration {

    @Bean(value = "defaultApi2")
    public Docket defaultApi2() {
        Docket docket = new Docket(DocumentationType.OAS_30)
                .apiInfo(new ApiInfoBuilder()
                        .title("巡检接口文档")
                        .description("控压控糖接口文档")
                        .termsOfServiceUrl("https://hitest.miaocloud.net/api/chronic/doc.html#/home")
                        .version("1.0.1")
                        .build())
                //分组名称
                .groupName("巡检接口文档")
                .select()
                //这里指定Controller扫描包路径
                .apis(RequestHandlerSelectors.basePackage("net.miaocloud.assistant.mcaichronic"))
                .paths(PathSelectors.any())
                .build();
        return docket;
    }
}
