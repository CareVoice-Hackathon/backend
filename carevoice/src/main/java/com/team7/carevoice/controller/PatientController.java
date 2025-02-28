package com.team7.carevoice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.team7.carevoice.dto.request.CreatePatientRequest;
import com.team7.carevoice.dto.response.ApiResponse;
import com.team7.carevoice.model.Patient;
import com.team7.carevoice.services.PatientService;

@RestController
@RequestMapping("/api")
public class PatientController {
	
	@Autowired
	private PatientService patientService;

	// For future use if accounts are implemented
	@GetMapping("/patients/{userId}")
	public ResponseEntity<?> getPatientsByUserId(
		@PathVariable(required = false) Long userId
	) {
		if (userId == null) {
			userId = 1L;
		}
		return ResponseEntity.ok(new ApiResponse<>(
			true, 
			"Fetched successfully",
			patientService.getPatientsByUserId(userId))
		);
	}

	@GetMapping("/patients")
	public ResponseEntity<?> getPatientsByUserId() {
		return ResponseEntity.ok(new ApiResponse<>(
			true, 
			"Fetched successfully",
			patientService.getPatientsByUserId(1L))
		);
	}

	@GetMapping("/patient/{patientId}")
	public ResponseEntity<?> getDocumentsByPatientId(
		@PathVariable(required = true) Long patientId
	) {
		//Stub
		return ResponseEntity.ok(new ApiResponse<>(true, "Not implemented yet"));
	}

	@PostMapping("/patient")
	public ResponseEntity<?> registerPatient(@RequestBody CreatePatientRequest request) {
		Patient newPatient = patientService.save(request);
		return ResponseEntity.ok(new ApiResponse<>(true, "Patient registered successfully", newPatient));
	}
}
