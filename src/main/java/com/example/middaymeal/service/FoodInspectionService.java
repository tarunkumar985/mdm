package com.example.middaymeal.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.middaymeal.entity.FoodInspection;
import com.example.middaymeal.repository.FoodInspectionRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FoodInspectionService {

    private final FoodInspectionRepository inspectionRepository;

    public FoodInspection create(FoodInspection inspection) {

        if (inspection.getInspector() == null) {
            throw new IllegalArgumentException("Inspector required");
        }

        return inspectionRepository.save(inspection);
    }

    public List<FoodInspection> getBySchool(Long schoolId) {
        return inspectionRepository.findBySchool_Id(schoolId);
    }
}