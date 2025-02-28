package com.team7.carevoice.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
public class HeadToToe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long patientId;

    @Column(nullable = false)
    private String patientName;

    @Column(nullable = false)
    private Date createdTime;

    @Column(nullable = false)
    private String neurological;

    @Column(nullable = false)
    private String heent;

    @Column(nullable = false)
    private String respiratory;

    @Column(nullable = false)
    private String cardiac;

    @Column(nullable = false)
    private String peripheralVascular;

    @Column(nullable = false)
    private String integumentary;

    @Column(nullable = false)
    private String musculoskeletal;

    @Column(nullable = false)
    private String gastrointestinal;

    @Column(nullable = false)
    private String genitourinary;

    @Column(nullable = false)
    private String sleepRest;

    @Column(nullable = false)
    private String psychosocial;

    // Default constructor
    public HeadToToe() {
    }

    // Getters and setters
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

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
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
