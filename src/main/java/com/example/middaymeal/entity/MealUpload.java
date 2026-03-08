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
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(
    name = "meal_uploads",
    uniqueConstraints = @UniqueConstraint(columnNames = {"school_id", "upload_date"})
)
@Getter @Setter @NoArgsConstructor
public class MealUpload {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "school_id")
    private School school;

    @Column(name = "upload_date", nullable = false)
    private LocalDate uploadDate;

    @Column(name = "kitchen_image_url")
    private String kitchenImageUrl;

    @Column(name = "food_image_url")
    private String foodImageUrl;

    @Column(name = "group_image_url")
    private String groupImageUrl;

    @ManyToOne
    @JoinColumn(name = "uploaded_by")
    private User uploadedBy;

    @Column(name = "upload_time")
    private LocalDateTime uploadTime;

    private String status; // RECEIVED, PROCESSED
}