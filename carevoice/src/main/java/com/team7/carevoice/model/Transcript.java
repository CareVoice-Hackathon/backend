package com.team7.carevoice.model;

import jakarta.persistence.*;

@Entity
public class Transcript extends Document {

    @Column(columnDefinition = "TEXT", nullable = false)
    private String body;

    @Column(nullable = false)
    private String audioFile;

    public Transcript() {
        super(DocumentType.TRANSCRIPT, null);
    }

    public Transcript(Patient patient, String body, String audioFile) {
        super(DocumentType.TRANSCRIPT, patient);
        this.body = body;
        this.audioFile = audioFile;
    }

    // Getters and Setters
    public String getBody() { return body; }
    public String getAudioFile() { return audioFile; }
    public void setBody(String body) { this.body = body; }
    public void setAudioFile(String audioFile) { this.audioFile = audioFile; }
}
