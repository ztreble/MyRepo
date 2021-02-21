package com.neu.config;


import com.neu.interceptors.CorsInterceptor;
import com.neu.interceptors.TokenInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by mark on 17/10/31.
 */
@Configuration
@EnableWebMvc
public class WebConfig extends WebMvcConfigurerAdapter {

    @Autowired
    private CorsInterceptor corsInterceptor;
    @Autowired
    private TokenInterceptor tokenInterceptor;

    /**
     * 拦截器管理
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(tokenInterceptor);
        registry.addInterceptor(corsInterceptor);
        super.addInterceptors(registry);
    }

    /**
     * 消除跨域
     *
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("POST", "GET", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("token", "accept", "content-type")
                .allowCredentials(false).maxAge(3600);
    }
}
