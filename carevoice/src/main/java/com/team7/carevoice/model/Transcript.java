package com.team7.carevoice.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "document_transcriptions")
public class Transcript {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String patientName;

    private Long patientId; // placeholder since we don't have a patient model yet
    private LocalDateTime createdTime;

    @Column(columnDefinition = "TEXT") // for large texts
    private String body;

    // Constructors
    public Transcript() {}

    public Transcript(Long patientId, String name, LocalDateTime createdTime, String type, String body) {
        this.patientId = patientId;
        this.patientName = name;
        this.createdTime = createdTime;
        this.body = body;

    }

    // Getters and Setters
    public String getName() {
        return patientName;
    }
    public void setName(String name) {
        this.patientName = name;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}