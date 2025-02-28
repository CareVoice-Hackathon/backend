package com.team7.carevoice.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "summaries")
public class Summary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "patient_id")
    private Long patientId;

    @Column(name = "created_time")
    private LocalDateTime createdTime;

    @Column(columnDefinition = "TEXT")
    private String body;

    // Getters and Setters
} 