package com.example.middaymeal.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.middaymeal.dto.MealUploadRequest;
import com.example.middaymeal.dto.MealUploadResponse;
import com.example.middaymeal.entity.MealUpload;
import com.example.middaymeal.service.Auditable;
import com.example.middaymeal.service.MealUploadService;
import com.example.middaymeal.service.SchoolService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/school/meals")
@RequiredArgsConstructor
public class MealUploadController {

    private final MealUploadService mealUploadService;
    private final SchoolService schoolService;

    @Auditable(
    	    action = "UPLOAD_MEAL",
    	    resource = "MEAL_UPLOAD"
    	)
    @PostMapping
    public ResponseEntity<MealUploadResponse> uploadMeal(
            @RequestParam Long schoolId,
            @RequestBody MealUploadRequest request
    ) {
        MealUpload upload = new MealUpload();
        upload.setSchool(schoolService.getById(schoolId));
        upload.setUploadDate(request.uploadDate());
        upload.setKitchenImageUrl(request.kitchenImageUrl());
        upload.setFoodImageUrl(request.foodImageUrl());
        upload.setGroupImageUrl(request.groupImageUrl());
        upload.setStatus("RECEIVED");

        MealUpload saved = mealUploadService.uploadMeal(upload);

        return ResponseEntity.ok(
                new MealUploadResponse(saved.getId(), saved.getStatus())
        );
    }
}