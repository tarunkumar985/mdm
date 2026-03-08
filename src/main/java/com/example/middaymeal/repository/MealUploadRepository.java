package com.example.middaymeal.repository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.middaymeal.entity.MealUpload;
@Repository
public interface MealUploadRepository extends JpaRepository<MealUpload, Long> {

    Optional<MealUpload> findBySchool_IdAndUploadDate(Long schoolId, LocalDate uploadDate);

    List<MealUpload> findBySchool_IdOrderByUploadDateDesc(Long schoolId);

    boolean existsBySchool_IdAndUploadDate(Long schoolId, LocalDate uploadDate);
}