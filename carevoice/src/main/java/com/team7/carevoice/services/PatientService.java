package com.team7.carevoice.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team7.carevoice.model.Patient;
import com.team7.carevoice.repository.PatientRepository;

import jakarta.transaction.Transactional;

@Service
public class PatientService {
	
	@Autowired
	private PatientRepository patientRepository;

	public Patient getPatientById(Long id) {
		return patientRepository.findById(id)
			.orElseThrow(() -> new IllegalArgumentException("Patient not found with id: " + id));
	}

	public List<Patient> getPatientsByUserId(Long userId) {
        return patientRepository.findByUserId(userId);
    }

	@Transactional
	public Patient save(Patient patient) {
		return patientRepository.save(patient);
	}
}
