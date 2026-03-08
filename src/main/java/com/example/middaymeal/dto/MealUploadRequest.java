package com.example.middaymeal.dto;

import java.time.LocalDate;

public record MealUploadRequest(
        LocalDate uploadDate,
        String kitchenImageUrl,
        String foodImageUrl,
        String groupImageUrl
) {}