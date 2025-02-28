package com.team7.carevoice.controller;

import com.team7.carevoice.dto.response.ApiResponse;
import com.team7.carevoice.model.HeadToToeAssessment;
import com.team7.carevoice.services.HeadToToeAssessmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/head-to-toe")
public class HeadToToeAssessmentController {

    private final HeadToToeAssessmentService assessmentService;

    @Autowired
    public HeadToToeAssessmentController(HeadToToeAssessmentService assessmentService) {
        this.assessmentService = assessmentService;
    }

    // GET endpoint - Retrieve an assessment by ID
    @GetMapping("/get/{id}")
    public ApiResponse<HeadToToeAssessment> getAssessmentById(@PathVariable Long id) {
        return assessmentService.getAssessmentById(id);
    }

    // PATCH endpoint - Update an assessment by ID
    @PatchMapping("/patch/{id}")
    public ApiResponse<HeadToToeAssessment> updateAssessment(@PathVariable Long id, @RequestBody HeadToToeAssessment updatedAssessment) {
        return assessmentService.updateAssessment(id, updatedAssessment);
    }
}
