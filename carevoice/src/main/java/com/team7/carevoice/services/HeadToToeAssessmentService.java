package com.team7.carevoice.services;

import com.team7.carevoice.dto.response.ApiResponse;
import com.team7.carevoice.model.HeadToToeAssessment;
import com.team7.carevoice.model.Patient;
import com.team7.carevoice.repository.HeadToToeAssessmentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class HeadToToeAssessmentService {

    private final HeadToToeAssessmentRepository headToToeAssessmentRepository;

    @Autowired
    public HeadToToeAssessmentService(HeadToToeAssessmentRepository headToToeAssessmentRepository) {
        this.headToToeAssessmentRepository = headToToeAssessmentRepository;
    }

    // GET endpoint - Retrieve an assessment by ID
    public ApiResponse<HeadToToeAssessment> getAssessmentById(Long id) {
        Optional<HeadToToeAssessment> assessment = headToToeAssessmentRepository.findById(id);

        if (assessment.isPresent()) {
            return new ApiResponse<>(true, "Assessment retrieved successfully", assessment.get());
        } else {
            return new ApiResponse<>(false, "Assessment not found", null);
        }
    }

    // PATCH endpoint - Update an existing assessment by ID
    public ApiResponse<HeadToToeAssessment> updateAssessment(Long id, HeadToToeAssessment updatedAssessment) {
        Optional<HeadToToeAssessment> existingAssessmentOpt = headToToeAssessmentRepository.findById(id);

        if (existingAssessmentOpt.isPresent()) {
            HeadToToeAssessment existingAssessment = existingAssessmentOpt.get();
            // Only update fields that are provided in the request 
            if (updatedAssessment.getNeurological() != null) {
                existingAssessment.setNeurological(updatedAssessment.getNeurological());
            }
            if (updatedAssessment.getHeent() != null) {
                existingAssessment.setHeent(updatedAssessment.getHeent());
            }
            if (updatedAssessment.getRespiratory() != null) {
                existingAssessment.setRespiratory(updatedAssessment.getRespiratory());
            }
            if (updatedAssessment.getCardiac() != null) {
                existingAssessment.setCardiac(updatedAssessment.getCardiac());
            }
            if (updatedAssessment.getPeripheralVascular() != null) {
                existingAssessment.setPeripheralVascular(updatedAssessment.getPeripheralVascular());
            }
            if (updatedAssessment.getIntegumentary() != null) {
                existingAssessment.setIntegumentary(updatedAssessment.getIntegumentary());
            }
            if (updatedAssessment.getMusculoskeletal() != null) {
                existingAssessment.setMusculoskeletal(updatedAssessment.getMusculoskeletal());
            }
            if (updatedAssessment.getGastrointestinal() != null) {
                existingAssessment.setGastrointestinal(updatedAssessment.getGastrointestinal());
            }
            if (updatedAssessment.getGenitourinary() != null) {
                existingAssessment.setGenitourinary(updatedAssessment.getGenitourinary());
            }
            if (updatedAssessment.getSleepRest() != null) {
                existingAssessment.setSleepRest(updatedAssessment.getSleepRest());
            }
            if (updatedAssessment.getPsychosocial() != null) {
                existingAssessment.setPsychosocial(updatedAssessment.getPsychosocial());
            }

            // Save the updated assessment
            headToToeAssessmentRepository.save(existingAssessment);

            return new ApiResponse<>(true, "Assessment updated successfully", existingAssessment);
        } else {
            return new ApiResponse<>(false, "Assessment not found", null);
        }
    }

  // Method to create and save a new assessment
    public HeadToToeAssessment createNewAssessment(Patient patient,
    String neurological, String heent, String respiratory, String cardiac,
    String peripheralVascular, String integumentary, String musculoskeletal,
    String gastrointestinal, String genitourinary, String sleepRest, String psychosocial) {

        
        // Create the new HeadToToeAssessment using the constructor
        HeadToToeAssessment newAssessment = new HeadToToeAssessment(
                patient,
                neurological,
                heent,
                respiratory,
                cardiac,
                peripheralVascular,
                integumentary,
                musculoskeletal,
                gastrointestinal,
                genitourinary,
                sleepRest,
                psychosocial
        );

        // Save to the repository and return the saved assessment
        return headToToeAssessmentRepository.save(newAssessment);
    }

}
