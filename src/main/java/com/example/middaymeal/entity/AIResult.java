package com.example.middaymeal.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "ai_results")
@Getter @Setter @NoArgsConstructor
public class AIResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(optional = false)
    @JoinColumn(name = "meal_upload_id")
    private MealUpload mealUpload;

    @Column(name = "face_count")
    private Integer faceCount;

    @Column(name = "hygiene_risk")
    private String hygieneRisk; // LOW, MEDIUM, HIGH

    @ElementCollection
    @CollectionTable(
        name = "ai_risk_flags",
        joinColumns = @JoinColumn(name = "ai_result_id")
    )
    @Column(name = "flag")
    private List<String> riskFlags;

    @Column(name = "processed_at")
    private LocalDateTime processedAt;
}