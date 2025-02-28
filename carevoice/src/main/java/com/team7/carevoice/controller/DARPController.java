package com.team7.carevoice.controller;

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
		return ResponseEntity.ok(new ApiResponse<>(
			true, 
			"Retrieved DARP", 
			darpService.getDARPById(id)));
	}

	@PatchMapping("/{id}")
	public ResponseEntity<?> updateDARP(@PathVariable Long id, @RequestBody DARP updatedDARP) {
		return ResponseEntity.ok(new ApiResponse<>(
			true, 
			"Updated successfully",
			darpService.updateDARP(id, updatedDARP)));
	}
}
