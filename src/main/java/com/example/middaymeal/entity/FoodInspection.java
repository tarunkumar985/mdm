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
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "food_inspections")
@Getter @Setter @NoArgsConstructor
public class FoodInspection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "school_id")
    private School school;

    @ManyToOne
    @JoinColumn(name = "meal_upload_id")
    private MealUpload mealUpload;

    @ManyToOne
    @JoinColumn(name = "inspector_id")
    private User inspector;

    @Column(name = "inspection_date")
    private LocalDate inspectionDate;

    private String foodQuality; // GOOD, AVERAGE, POOR

    private Boolean hygieneObserved;
    private Boolean utensilsClean;
    private Boolean kitchenClean;

    private String inspectionNotes;

    private Boolean actionRequired = false;
    private String actionDetails;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}