package com.example.middaymeal.entity;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "schools")
@Getter @Setter @NoArgsConstructor
public class School {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "school_code", nullable = false, unique = true)
    private String schoolCode;

    private String name;
    private String district;
    private String block;
    private String state;
    private String address;
    private Integer totalStudents;

    @Column(name = "is_active")
    private Boolean active = true;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
}