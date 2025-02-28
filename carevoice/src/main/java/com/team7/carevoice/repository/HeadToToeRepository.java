package com.team7.carevoice.repository;

import com.team7.carevoice.model.HeadToToe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HeadToToeRepository extends JpaRepository<HeadToToe, Long> {
    HeadToToe findByPatientId(Long patientId);
} 