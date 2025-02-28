package com.team7.carevoice.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdTime;

    @Enumerated(EnumType.STRING)
    private DocumentType type;

    public Document() {
        this.createdTime = new Date();
    }

    public Document(DocumentType type, Patient patient) {
        this();
        this.type = type;
        this.patient = patient;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public Patient getPatient() { return patient; }
    public Date getCreatedTime() { return createdTime; }
    public DocumentType getType() { return type; }

    public void setId(Long id) { this.id = id; }
    public void setPatient(Patient patient) { this.patient = patient; }
    public void setCreatedTime(Date createdTime) { this.createdTime = createdTime; }
    public void setType(DocumentType type) { this.type = type; }
}
