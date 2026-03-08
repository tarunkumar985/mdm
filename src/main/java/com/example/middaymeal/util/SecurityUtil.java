package com.example.middaymeal.util;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.example.middaymeal.entity.User;
import com.example.middaymeal.service.UserService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class SecurityUtil {

    private final UserService userService;

    public User getCurrentUser() {
        String username = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();
        return userService.getByUsername(username);
    }
}