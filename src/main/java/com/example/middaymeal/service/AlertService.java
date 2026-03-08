package com.example.middaymeal.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.middaymeal.entity.Alert;
import com.example.middaymeal.entity.MealUpload;
import com.example.middaymeal.entity.School;
import com.example.middaymeal.entity.User;
import com.example.middaymeal.repository.AlertRepository;
import com.example.middaymeal.util.SecurityUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AlertService {

    private final AlertRepository alertRepository;
    
    
    private SecurityUtil securityUtil;

    public void createHighRiskAlert(School school, MealUpload upload) {
        Alert alert = new Alert();
        alert.setSchool(school);
        alert.setMealUpload(upload);
        alert.setAlertType("HIGH_HYGIENE_RISK");
        alert.setSeverity("HIGH");
        alert.setMessage("High hygiene risk detected by system");

        alertRepository.save(alert);
    }

    public void createNoUploadAlert(School school) {
        Alert alert = new Alert();
        alert.setSchool(school);
        alert.setAlertType("NO_UPLOAD");
        alert.setSeverity("MEDIUM");
        alert.setMessage("No meal upload received today");

        alertRepository.save(alert);
    }

    public List<Alert> getOpenAlerts(Long schoolId) {
        return alertRepository.findBySchool_IdAndResolvedFalse(schoolId);
    }
    
    
    public List<Alert> getAlertsForCurrentUser() {

        User user = securityUtil.getCurrentUser();

        if (user.getRole().equals("SCHOOL")) {
            return alertRepository
                    .findBySchool_IdAndResolvedFalse(
                            user.getSchool().getId());
        }

        // OFFICER / ADMIN
        return alertRepository.findAll();
    }
}