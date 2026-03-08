package com.example.middaymeal.dto;

import java.time.LocalDate;

public record DailyReportResponse(
        LocalDate date,
        Integer studentsEstimated,
        String hygieneRisk,
        String remarks
) {}