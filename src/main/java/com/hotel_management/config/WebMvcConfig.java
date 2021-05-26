package com.hotel_management.config;

import com.hotel_management.service.UserService;
import lombok.RequiredArgsConstructor;
import ma.glasnost.orika.MapperFacade;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
@RequiredArgsConstructor
public class WebMvcConfig extends WebMvcConfigurationSupport {

    private final MapperFacade mapperFacade;
    private final UserService userService;

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new HandlerInterceptorImpl(mapperFacade, userService));
    }
}
