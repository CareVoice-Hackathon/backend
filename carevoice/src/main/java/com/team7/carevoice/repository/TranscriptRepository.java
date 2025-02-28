package com.team7.carevoice.repository;

import com.team7.carevoice.model.Transcript;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TranscriptRepository extends JpaRepository<Transcript, Long> {
    List<Transcript> findByPatientId(Long patientId);
}
