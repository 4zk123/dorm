package com.cduestc.dorm.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
@Configuration
public class CorsConfig {
    //设置跨域请求最大有效时长
    private static final long Max_AGE = 24*60*60;
    @Bean
    public CorsFilter corsFilter(){
        UrlBasedCorsConfigurationSource source= new UrlBasedCorsConfigurationSource();
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("http://localhost:8080");//设置访问源地址
        corsConfiguration.addAllowedHeader("*");//设置访问源请求
        corsConfiguration.addAllowedMethod("*");//设置访问源请求方法
        corsConfiguration.setMaxAge(Max_AGE);
        source.registerCorsConfiguration("/**",corsConfiguration);//对接口配置跨域设置
        return new CorsFilter(source);
    }
}
