package com.example.middaymeal.service;

import org.springframework.stereotype.Service;

import com.example.middaymeal.entity.AIResult;
import com.example.middaymeal.repository.AIResultRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AIResultService {

    private final AIResultRepository aiResultRepository;
    private final AlertService alertService;

    public AIResult saveResult(AIResult result) {

        AIResult saved = aiResultRepository.save(result);

        if ("HIGH".equals(result.getHygieneRisk())) {
            alertService.createHighRiskAlert(
                    result.getMealUpload().getSchool(),
                    result.getMealUpload()
            );
        }

        return saved;
    }
}