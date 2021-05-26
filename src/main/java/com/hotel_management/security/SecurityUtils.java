package com.hotel_management.security;

import com.hotel_management.model.User;
import lombok.experimental.UtilityClass;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Objects;

@UtilityClass
public class SecurityUtils {

    public static User getUserFromContext() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        if (Objects.nonNull(principal) && principal instanceof User) {
            return (User) principal;
        }
        return null;
    }
}
