package com.example.middaymeal.dto;

import java.time.LocalDate;

public record FoodInspectionRequest(
        Long schoolId,
        LocalDate inspectionDate,
        String foodQuality,
        Boolean hygieneObserved,
        Boolean utensilsClean,
        Boolean kitchenClean,
        String inspectionNotes
) {}