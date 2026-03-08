package com.example.middaymeal.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(
    name = "daily_reports",
    uniqueConstraints = @UniqueConstraint(columnNames = {"school_id", "report_date"})
)
@Getter @Setter @NoArgsConstructor
public class DailyReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "school_id")
    private School school;

    @Column(name = "report_date", nullable = false)
    private LocalDate reportDate;

    @OneToOne
    @JoinColumn(name = "meal_upload_id")
    private MealUpload mealUpload;

    @Column(name = "total_students_estimated")
    private Integer totalStudentsEstimated;

    @Column(name = "hygiene_risk")
    private String hygieneRisk;

    @Column(name = "alert_generated")
    private Boolean alertGenerated = false;

    private String remarks;

    @Column(name = "generated_by")
    private String generatedBy; // SYSTEM / OFFICER

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}