package com.yl.soft.common.interceptor;

import com.yl.soft.common.unified.redis.RedisService;
import com.yl.soft.service.EhbAudienceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Autowired
    RedisService redisService;
    
    @Autowired
    EhbAudienceService ehbAudienceService;

    @Bean
    public InterceptorConfig getMyWebMvcConfig() {
        InterceptorConfig interceptorConfig = new InterceptorConfig() {
            //注册拦截器
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
               registry.addInterceptor(new ApiInterceptor()).addPathPatterns("/**");

                //配置后台登录拦截器
            registry.addInterceptor(new PlatformInterceptor(redisService)).addPathPatterns("/platform/**")
                      .excludePathPatterns("/platform/logout","/platform/login");

                //配置APP登录拦截器
                registry.addInterceptor(new AppLoginInterceptor(redisService,ehbAudienceService)).addPathPatterns("/api/**")
                    .excludePathPatterns("/api/login","/api/credentialsDownUrl","/api/emailverificationcode")
                        .excludePathPatterns("/api/userAgreement","/api/hottitleList","/api/randExibitionList")
                        .excludePathPatterns("/api/backstageRecommendExibitionList")
                        .excludePathPatterns("/api/articleList","/api/ehbBannerList","/api/guestList","/api/goodsList")
                    .excludePathPatterns("/api/listLabel","/api/upLoadByHttp","/api/upLoadByHttp2","/api/showFile","/api/down");
            }
        };
        return interceptorConfig;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("doc.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}