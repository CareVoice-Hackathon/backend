package com.team7.carevoice.repository;

import com.team7.carevoice.model.Transcript;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TranscriptRepository extends JpaRepository<Transcript, Long> {
} 