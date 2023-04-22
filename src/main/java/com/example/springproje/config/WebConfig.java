package com.example.springproje.config;

import com.example.springproje.annotation.resolver.CurrentUserIdHandlerMethodArgResolver;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;
import java.util.List;

public class WebConfig implements WebMvcConfigurer {
    @Resource
    private CurrentUserIdHandlerMethodArgResolver currentUserIdHandlerMethodArgResolver;

    @Override
    public void addArgumentResolvers(@NotNull List<HandlerMethodArgumentResolver> resolvers) {
        //注册@CurrentUserId注解的实现类
        resolvers.add(new CurrentUserIdHandlerMethodArgResolver());
    }
}
