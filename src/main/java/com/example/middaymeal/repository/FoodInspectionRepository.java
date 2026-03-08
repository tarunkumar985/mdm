package com.example.middaymeal.repository;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.middaymeal.entity.FoodInspection;
@Repository
public interface FoodInspectionRepository extends JpaRepository<FoodInspection, Long> {

    List<FoodInspection> findBySchool_Id(Long schoolId);

    List<FoodInspection> findByInspectionDate(LocalDate inspectionDate);

    List<FoodInspection> findByInspector_Id(Long inspectorId);
}