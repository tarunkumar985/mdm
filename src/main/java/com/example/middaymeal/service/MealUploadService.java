package com.example.middaymeal.service;

import java.time.LocalDate;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import com.example.middaymeal.entity.MealUpload;
import com.example.middaymeal.entity.User;
import com.example.middaymeal.repository.MealUploadRepository;
import com.example.middaymeal.util.SecurityUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MealUploadService {

    private final MealUploadRepository mealUploadRepository;
    
    private SecurityUtil securityUtil;

    public MealUpload uploadMeal(MealUpload upload) {

        boolean exists = mealUploadRepository.existsBySchool_IdAndUploadDate(
                upload.getSchool().getId(),
                upload.getUploadDate()
        );

        if (exists) {
            throw new IllegalStateException("Meal already uploaded for today");
        }

        MealUpload saved = mealUploadRepository.save(upload);

        return saved;
    }

    public MealUpload getTodayUpload(Long schoolId, LocalDate date) {
        return mealUploadRepository
                .findBySchool_IdAndUploadDate(schoolId, date)
                .orElse(null);
    }
    
    public MealUpload getUpload(Long uploadId) {

        MealUpload upload = mealUploadRepository.findById(uploadId)
                .orElseThrow(() -> new RuntimeException("Not found"));

        User current = securityUtil.getCurrentUser();

        if (current.getRole().equals("SCHOOL")) {
            if (!upload.getSchool().getId()
                    .equals(current.getSchool().getId())) {
                throw new AccessDeniedException("Forbidden");
            }
        }

        return upload;
    }
}