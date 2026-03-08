package com.example.middaymeal.dto;

public record CreateUserRequest(
        String username,
        String password,
        String role,      // SCHOOL, OFFICER, ADMIN
        Long schoolId     // only for SCHOOL role
) {}