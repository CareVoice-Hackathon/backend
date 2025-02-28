package com.team7.carevoice.repository;

import com.team7.carevoice.model.HeadToToe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HeadToToeRepository extends JpaRepository<HeadToToe, Long> {
    List<HeadToToe> findByPatientId(Long patientId);
}
