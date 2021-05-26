package com.hotel_management.config;

import com.hotel_management.dto.UserDto;
import com.hotel_management.model.User;
import com.hotel_management.service.UserService;
import lombok.RequiredArgsConstructor;
import ma.glasnost.orika.MapperFacade;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.Objects;

@RequiredArgsConstructor
public class HandlerInterceptorImpl implements HandlerInterceptor {

    private final MapperFacade mapperFacade;
    private final UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        if (Objects.nonNull(modelAndView)) {
            Principal principal = request.getUserPrincipal();
            if (Objects.nonNull(principal)) {
                User user = (User) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();
                modelAndView.addObject("authUser", mapperFacade.map(userService.getById(user.getId()), UserDto.class));
            }
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
