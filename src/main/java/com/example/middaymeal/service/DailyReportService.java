package com.example.middaymeal.service;

import java.time.LocalDate;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import com.example.middaymeal.entity.DailyReport;
import com.example.middaymeal.entity.MealUpload;
import com.example.middaymeal.entity.User;
import com.example.middaymeal.repository.AIResultRepository;
import com.example.middaymeal.repository.DailyReportRepository;
import com.example.middaymeal.repository.MealUploadRepository;
import com.example.middaymeal.util.SecurityUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DailyReportService {

    private final DailyReportRepository dailyReportRepository;
    private final MealUploadRepository mealUploadRepository;
    private final AIResultRepository aiResultRepository;
    private SecurityUtil securityUtil;

    public DailyReport generate(Long schoolId, LocalDate date) {

        MealUpload upload =
                mealUploadRepository.findBySchool_IdAndUploadDate(schoolId, date)
                        .orElse(null);

        DailyReport report = new DailyReport();
        report.setReportDate(date);

        if (upload == null) {
            report.setAlertGenerated(true);
            report.setRemarks("No meal upload for the day");
            return dailyReportRepository.save(report);
        }

        report.setMealUpload(upload);

        aiResultRepository.findByMealUpload_Id(upload.getId())
                .ifPresent(ai -> {
                    report.setTotalStudentsEstimated(ai.getFaceCount());
                    report.setHygieneRisk(ai.getHygieneRisk());
                });

        return dailyReportRepository.save(report);
    }
    
    
    public DailyReport getReport(Long schoolId, LocalDate date) {

        User user = securityUtil.getCurrentUser();

        if (user.getRole().equals("SCHOOL")
            && !user.getSchool().getId().equals(schoolId)) {
            throw new AccessDeniedException("Forbidden");
        }

        return dailyReportRepository
                .findBySchool_IdAndReportDate(schoolId, date)
                .orElseThrow(() -> new RuntimeException("Not found"));
    }
}