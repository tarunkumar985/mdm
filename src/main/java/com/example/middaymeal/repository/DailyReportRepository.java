package com.example.middaymeal.repository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.middaymeal.entity.DailyReport;
@Repository
public interface DailyReportRepository extends JpaRepository<DailyReport, Long> {

    Optional<DailyReport> findBySchool_IdAndReportDate(Long schoolId, LocalDate reportDate);

    List<DailyReport> findBySchool_IdOrderByReportDateDesc(Long schoolId);
}