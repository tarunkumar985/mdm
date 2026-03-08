package com.example.middaymeal.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.middaymeal.dto.CreateUserRequest;
import com.example.middaymeal.entity.User;
import com.example.middaymeal.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // 🔐 Only ADMIN / OFFICER can create users
    @PreAuthorize("hasAnyRole('ADMIN','OFFICER')")
    @PostMapping
    public ResponseEntity<?> createUser(
            @RequestBody CreateUserRequest request
    ) {
        User user = userService.createUser(request);

        return ResponseEntity.ok(
                java.util.Map.of(
                        "id", user.getId(),
                        "username", user.getUsername(),
                        "role", user.getRole()
                )
        );
    }
}