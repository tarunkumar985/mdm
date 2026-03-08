package com.example.middaymeal.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.middaymeal.dto.AlertResponse;
import com.example.middaymeal.dto.DailyReportResponse;
import com.example.middaymeal.service.AlertService;
import com.example.middaymeal.service.Auditable;
import com.example.middaymeal.service.DailyReportService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/officer")
@RequiredArgsConstructor
public class OfficerController {

    private final AlertService alertService;
    private final DailyReportService dailyReportService;

    @GetMapping("/alerts")
    public List<AlertResponse> getAlerts(@RequestParam Long schoolId) {
        return alertService.getOpenAlerts(schoolId)
                .stream()
                .map(a -> new AlertResponse(
                        a.getId(),
                        a.getSchool().getName(),
                        a.getAlertType(),
                        a.getSeverity(),
                        a.getMessage()
                ))
                .toList();
    }

    
    @Auditable(
    	    action = "VIEW_DAILY_REPORT",
    	    resource = "DAILY_REPORT"
    	)
    @GetMapping("/daily-report")
    public DailyReportResponse getDailyReport(
            @RequestParam Long schoolId,
            @RequestParam LocalDate date
    ) {
        var report = dailyReportService.generate(schoolId, date);

        return new DailyReportResponse(
                report.getReportDate(),
                report.getTotalStudentsEstimated(),
                report.getHygieneRisk(),
                report.getRemarks()
        );
    }
}