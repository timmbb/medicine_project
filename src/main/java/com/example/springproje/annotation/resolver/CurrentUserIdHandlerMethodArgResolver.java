package com.example.springproje.annotation.resolver;

import com.example.springproje.annotation.CurrentUserId;
import org.jetbrains.annotations.NotNull;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;

public class CurrentUserIdHandlerMethodArgResolver implements HandlerMethodArgumentResolver {
    /**
     * 判断是否支持使用@CurrentUserId注解的参数
     */
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        //如果该参数注解有@CurrentUser且参数类型是User
        return parameter.getParameterAnnotation(CurrentUserId.class) != null && parameter.getParameterType() == Integer.class;
    }

    /**
     * 注入参数值
     */
    @Override
    public Object resolveArgument(@NotNull MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        //取得HttpServletRequest
        HttpServletRequest request= (HttpServletRequest) webRequest.getNativeRequest();
        //取出session中的User
        return (Integer)request.getSession().getAttribute("id");
    }
}
