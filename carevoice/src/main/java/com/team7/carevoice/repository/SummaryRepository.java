package com.team7.carevoice.repository;

import com.team7.carevoice.model.Summary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SummaryRepository extends JpaRepository<Summary, Long> {
    Summary findByPatientId(Long patientId);
} 