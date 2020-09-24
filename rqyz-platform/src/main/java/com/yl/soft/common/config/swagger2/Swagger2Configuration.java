package com.yl.soft.common.config.swagger2;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author : huangjunhua
 * @date : 2019-08-23 11:48
 * Description : Swagger2配置类
 * Notice :
 */
@Configuration
@EnableSwagger2
public class Swagger2Configuration {

    @Bean
    public Docket createEquApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                //.apis(RequestHandlerSelectors.withClassAnnotation(Api.class))//这是注意的代码
                .apis(RequestHandlerSelectors.basePackage("com.yl.soft.controller.api"))
                .paths(PathSelectors.any())
                .build()
                .groupName("C端");
    }

//    @Bean
//    public Docket createCApi() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .apiInfo(apiInfo())
//                .select()
//                //.apis(RequestHandlerSelectors.withClassAnnotation(Api.class))//这是注意的代码
//                .apis(RequestHandlerSelectors.basePackage("com.yl.soft.controller.app"))
//                .paths(PathSelectors.any())
//                .build()
//                .groupName("C端");
//    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("燃气云展接口文档")
                .description("燃气云展系统")
                .contact(new Contact("huangjunhua", "", "740920822@qq.com"))
                //.termsOfServiceUrl("http://www.xxx.com")
                .version("1.0")
                .build();
    }
}