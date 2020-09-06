package org.zhaoyangli.ravenote.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

//For configuring interceptors before and after page loads
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Resource
    SessionInterceptor sessionInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(sessionInterceptor).addPathPatterns("/**").excludePathPatterns("/signin"); //All of the pages of this project will go to sessionInterceptor
    }
}