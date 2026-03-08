package com.example.middaymeal.dto;
public record AlertResponse(
        Long alertId,
        String schoolName,
        String alertType,
        String severity,
        String message
) {}