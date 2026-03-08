package com.example.middaymeal.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "alerts")
@Getter @Setter @NoArgsConstructor
public class Alert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "school_id")
    private School school;

    @ManyToOne
    @JoinColumn(name = "meal_upload_id")
    private MealUpload mealUpload;

    @Column(name = "alert_type")
    private String alertType; // NO_UPLOAD, HIGH_RISK

    private String severity; // LOW, MEDIUM, HIGH

    private String message;

    @Column(name = "is_resolved")
    private Boolean resolved = false;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}