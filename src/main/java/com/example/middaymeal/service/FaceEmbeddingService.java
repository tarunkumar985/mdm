package com.example.middaymeal.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.middaymeal.entity.FaceEmbedding;
import com.example.middaymeal.repository.FaceEmbeddingRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FaceEmbeddingService {

    private final FaceEmbeddingRepository repository;

    public FaceEmbedding save(FaceEmbedding embedding) {
        return repository.save(embedding);
    }

    public List<FaceEmbedding> getBySchool(Long schoolId) {
        return repository.findBySchool_Id(schoolId);
    }

    public void deleteBySchool(Long schoolId) {
        repository.deleteBySchool_Id(schoolId);
    }
}