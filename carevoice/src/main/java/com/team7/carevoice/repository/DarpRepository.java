package com.team7.carevoice.repository;

import com.team7.carevoice.model.Darp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DarpRepository extends JpaRepository<Darp, Long> {
    List<Darp> findByPatientId(Long patientId);
}
