package com.team7.carevoice.model;

import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String ahn;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dob;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Document> documents;

    // Constructors
    public Patient() {}

    public Patient(String name, String ahn, Date dob) {
        this.name = name;
        this.ahn = ahn;
        this.dob = dob;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public String getName() { return name; }
    public String getAhn() { return ahn; }
    public Date getDob() { return dob; }
    public List<Document> getDocuments() { return documents; }

    public void setId(Long id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setAhn(String ahn) { this.ahn = ahn; }
    public void setDob(Date dob) { this.dob = dob; }
    public void setDocuments(List<Document> documents) { this.documents = documents; }
}
