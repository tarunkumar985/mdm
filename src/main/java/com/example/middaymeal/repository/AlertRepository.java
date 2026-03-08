package com.example.middaymeal.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.middaymeal.entity.Alert;
@Repository
public interface AlertRepository extends JpaRepository<Alert, Long> {

    List<Alert> findBySchool_IdAndResolvedFalse(Long schoolId);

    List<Alert> findBySeverityAndResolvedFalse(String severity);

    List<Alert> findByMealUpload_Id(Long mealUploadId);
}