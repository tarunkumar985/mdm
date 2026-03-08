package com.example.middaymeal.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.middaymeal.entity.AIResult;

@Repository
public interface AIResultRepository extends JpaRepository<AIResult, Long> {

    Optional<AIResult> findByMealUpload_Id(Long mealUploadId);
}