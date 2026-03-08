package com.example.middaymeal.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.middaymeal.dto.FoodInspectionRequest;
import com.example.middaymeal.entity.FoodInspection;
import com.example.middaymeal.service.FoodInspectionService;
import com.example.middaymeal.service.SchoolService;
import com.example.middaymeal.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/inspection")
@RequiredArgsConstructor
public class InspectionController {

    private final FoodInspectionService inspectionService;
    private final SchoolService schoolService;
    private final UserService userService;

    @PostMapping
    public ResponseEntity<?> createInspection(
            @RequestParam Long inspectorId,
            @RequestBody FoodInspectionRequest request
    ) {
        FoodInspection inspection = new FoodInspection();
        inspection.setSchool(schoolService.getById(request.schoolId()));
        inspection.setInspectionDate(request.inspectionDate());
        inspection.setFoodQuality(request.foodQuality());
        inspection.setHygieneObserved(request.hygieneObserved());
        inspection.setUtensilsClean(request.utensilsClean());
        inspection.setKitchenClean(request.kitchenClean());
        inspection.setInspectionNotes(request.inspectionNotes());
        inspection.setInspector(userService.getByUsername("INSPECTOR")); // placeholder

        return ResponseEntity.ok(
                inspectionService.create(inspection)
        );
    }
}