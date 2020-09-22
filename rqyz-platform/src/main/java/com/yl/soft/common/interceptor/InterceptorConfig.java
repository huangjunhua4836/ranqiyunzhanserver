//package com.yl.soft.common.interceptor;
//
//import com.yl.soft.common.redis.RedisService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Configuration
//public class InterceptorConfig implements WebMvcConfigurer {
//
//    @Autowired
//    RedisService redisService;
//
//    @Bean
//    public InterceptorConfig getMyWebMvcConfig() {
//        InterceptorConfig interceptorConfig = new InterceptorConfig() {
//            //注册拦截器
//            @Override
//            public void addInterceptors(InterceptorRegistry registry) {
////                registry.addInterceptor(new SameUrlDataInterceptor()).addPathPatterns("/**");
////                registry.addInterceptor(new ApiInterceptor()).addPathPatterns("/**");
//
//                //配置设备登录拦截器
//                registry.addInterceptor(new TracebacktoLoginInterceptor(redisService)).addPathPatterns("/tracebackto/api/**")
//                        .excludePathPatterns("/tracebackto/api/login","/tracebackto/api/logout");
//
//                //配置设备登录拦截器
//                registry.addInterceptor(new AppLoginInterceptor(redisService)).addPathPatterns("/app/api/**");
//            }
//        };
//        return interceptorConfig;
//    }
//}