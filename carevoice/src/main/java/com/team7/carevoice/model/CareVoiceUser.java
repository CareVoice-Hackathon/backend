package com.team7.carevoice.model;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Represents a user in the AcmePlex movie ticket reservation system.
 * Each user has a unique ID, email, password, first name, and last name.
 */
@Entity
public class CareVoiceUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Automatically generated unique ID
    private Long id;

    @OneToMany(mappedBy = "carevoiceuser")
    private List<Patient> patients;

    @Column(nullable = false, unique = true) // Ensure email is unique and non-null
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private Date dateRegistered;

    public CareVoiceUser() {

    }

    /**
     * Constructs a new AcmePlexUser with the specified email, password, first name, and last name.
     *
     * @param email     the email of the user (must be unique)
     * @param password  the encrypted password of the user
     * @param firstName the first name of the user
     * @param lastName  the last name of the user
     */
    public CareVoiceUser(String email,
                        String password,
                        String firstName,
                        String lastName) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        dateRegistered = new Date();
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
