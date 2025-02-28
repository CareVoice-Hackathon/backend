package com.team7.carevoice.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "head_to_toe")
public class HeadToToe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "patient_id")
    private Long patientId;

    @Column(name = "created_time")
    private LocalDateTime createdTime;

    private String neurological;
    private String HEENT;
    private String respiratory;
    private String cardiac;
    
    @Column(name = "peripheral_vascular")
    private String peripheralVascular;
    
    private String integumentary;
    private String musculoskeletal;
    private String gastrointestinal;
    private String genitourinary;
    
    @Column(name = "sleep_rest")
    private String sleepRest;
    
    private String psychosocial;

    // Getters and Setters
} 