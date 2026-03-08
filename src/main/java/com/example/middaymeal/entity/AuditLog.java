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
@Table(name = "audit_logs")
@Getter @Setter @NoArgsConstructor
public class AuditLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String role;

    private String action;
    private String resource;
    private String resourceRef;

    private String ipAddress;
    private String status; // SUCCESS / DENIED

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}