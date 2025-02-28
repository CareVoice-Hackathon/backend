package com.team7.carevoice.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.team7.carevoice.dto.response.ApiResponse;
import com.team7.carevoice.model.DARP;
import com.team7.carevoice.services.DARPService;

@RestController
@RequestMapping("/api/DARP")
public class DARPController {
	
	@Autowired
	private DARPService darpService;

	@GetMapping("/{id}")
	public ResponseEntity<?> getDARPById(@PathVariable Long id) {

		DARP darp = darpService.getDARPById(id);

		Map<String, Object> response = new HashMap<>();
        response.put("createdTime", darp.getCreatedTime());
		response.put("patientName", darp.getPatient().getName());
		response.put("patientId", darp.getPatient().getId());

        Map<String, String> body = new HashMap<>();
        body.put("data", darp.getData());
        body.put("action", darp.getAction());
        body.put("response", darp.getResponse());
        body.put("plan", darp.getPlan());

        response.put("body", body);

		return ResponseEntity.ok(new ApiResponse<>(
			true, 
			"Retrieved DARP", 
			response));
	}

	@PatchMapping("/{id}")
	public ResponseEntity<?> updateDARP(@PathVariable Long id, @RequestBody Map<String, String> updatedBody) {
		return ResponseEntity.ok(new ApiResponse<>(
			true, 
			"Updated successfully",
			darpService.updateDARP(id, updatedBody)));
	}
}
