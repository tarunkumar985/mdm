package com.example.middaymeal.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "face_embeddings")
@Getter @Setter @NoArgsConstructor
public class FaceEmbedding {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "school_id")
    private School school;

    @Column(name = "student_ref")
    private String studentRef;

    @Lob
    private byte[] embedding;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}