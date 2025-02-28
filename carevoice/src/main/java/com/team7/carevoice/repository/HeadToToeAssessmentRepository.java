package com.team7.carevoice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.team7.carevoice.model.HeadToToeAssessment;
import com.team7.carevoice.model.Patient;

@Repository
public interface HeadToToeAssessmentRepository extends JpaRepository<HeadToToeAssessment, Long> {
    HeadToToeAssessment findByPatient(Patient patient);
}
