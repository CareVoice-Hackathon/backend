package com.team7.carevoice.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;

@Entity
public class HeadToToeAssessment {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private LocalDateTime createdTime;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    @JsonBackReference
    @JsonManagedReference
    private Patient patient;
    
    private String neurological;
    private String heent; // Head, Eyes, Ears, Nose, Throat
    private String respiratory;
    private String cardiac;
    private String peripheralVascular;
    private String integumentary;
    private String musculoskeletal;
    private String gastrointestinal;
    private String genitourinary;
    private String sleepRest;
    private String psychosocial;

     @PrePersist
    protected void onCreate() {
        this.createdTime = LocalDateTime.now();
    }
     @PrePersist
    protected void onCreate() {
        this.createdTime = LocalDateTime.now();
    }
    
    public HeadToToeAssessment() {
    }

    public HeadToToeAssessment(Patient patient,
    public HeadToToeAssessment(Patient patient,
        String neurological, String heent, String respiratory, String cardiac,
        String peripheralVascular, String integumentary, String musculoskeletal,
        String gastrointestinal, String genitourinary, String sleepRest, String psychosocial) {
            this.patient = patient;
            this.patient = patient;
            this.neurological = neurological;
            this.heent = heent;
            this.respiratory = respiratory;
            this.cardiac = cardiac;
            this.peripheralVascular = peripheralVascular;
            this.integumentary = integumentary;
            this.musculoskeletal = musculoskeletal;
            this.gastrointestinal = gastrointestinal;
            this.genitourinary = genitourinary;
            this.sleepRest = sleepRest;
            this.psychosocial = psychosocial;
        
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime; 
    }

    public Patient getPatient() {
        return patient;
    public Patient getPatient() {
        return patient;
    }

    public void setPatientId(Patient patient) {
        this.patient = patient;
    public void setPatientId(Patient patient) {
        this.patient = patient;
    }

    public String getNeurological() {
        return neurological;
    }

    public void setNeurological(String neurological) {
        this.neurological = neurological;
    }

    public String getHeent() {
        return heent;
    }

    public void setHeent(String heent) {
        this.heent = heent;
    }

    public String getRespiratory() {
        return respiratory;
    }

    public void setRespiratory(String respiratory) {
        this.respiratory = respiratory;
    }

    public String getCardiac() {
        return cardiac;
    }

    public void setCardiac(String cardiac) {
        this.cardiac = cardiac;
    }

    public String getPeripheralVascular() {
        return peripheralVascular;
    }

    public void setPeripheralVascular(String peripheralVascular) {
        this.peripheralVascular = peripheralVascular;
    }

    public String getIntegumentary() {
        return integumentary;
    }

    public void setIntegumentary(String integumentary) {
        this.integumentary = integumentary;
    }

    public String getMusculoskeletal() {
        return musculoskeletal;
    }

    public void setMusculoskeletal(String musculoskeletal) {
        this.musculoskeletal = musculoskeletal;
    }

    public String getGastrointestinal() {
        return gastrointestinal;
    }

    public void setGastrointestinal(String gastrointestinal) {
        this.gastrointestinal = gastrointestinal;
    }

    public String getGenitourinary() {
        return genitourinary;
    }

    public void setGenitourinary(String genitourinary) {
        this.genitourinary = genitourinary;
    }

    public String getSleepRest() {
        return sleepRest;
    }

    public void setSleepRest(String sleepRest) {
        this.sleepRest = sleepRest;
    }

    public String getPsychosocial() {
        return psychosocial;
    }

    public void setPsychosocial(String psychosocial) {
        this.psychosocial = psychosocial;
    }
}
