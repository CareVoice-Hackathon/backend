package com.team7.carevoice.model;

import jakarta.persistence.*;

@Entity
public class Summary extends Document {

    @Column(columnDefinition = "TEXT", nullable = false)
    private String body;

    public Summary() {
        super(DocumentType.SUMMARY, null);
    }

    public Summary(Patient patient, String body) {
        super(DocumentType.SUMMARY, patient);
        this.body = body;
    }

    // Getters and Setters
    public String getBody() { return body; }
    public void setBody(String body) { this.body = body; }
}
