package com.aitguigu.dataSecure.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author LucyChen
 * @date 2024-2-15
 * @desc:
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // 对所有请求启用CORS
                .allowedOrigins("*") // 允许来自 http://localhost:8080 的跨域请求
                .allowedMethods("GET", "POST", "PUT", "DELETE") // 允许的HTTP方法
                .allowedHeaders("*"); // 允许的头部信息
    }
}
