package com.team7.carevoice.services;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.team7.carevoice.dto.request.TranscriptRequest;
import com.team7.carevoice.dto.response.ApiResponse;
import com.team7.carevoice.model.Transcript;
import com.team7.carevoice.repository.TranscriptRepository;

@Service
public class TranscriptService {

    private final TranscriptRepository transcriptRepository;

    public TranscriptService(TranscriptRepository transcriptRepository) {
        this.transcriptRepository = transcriptRepository;
    }

    /**
     * Creates or updates a transcript record with the given primary key (transcriptId).
     *
     * @param transcriptId the DB primary key from the URL
     * @param request the request body
     * @return an ApiResponse containing whether it succeeded and the Transcript data (or error message)
     */
    public ApiResponse<Transcript> createTranscript(TranscriptRequest request) {
        try {
            // 1) Construct or update the Transcript object
            Transcript transcript = new Transcript();


            
            transcript.setName(request.getPatientName());
            transcript.setPatientId(request.getPatientId());
            transcript.setBody(request.getBody());

            // 2) Save to DB
            Transcript savedTranscript = transcriptRepository.save(transcript);

            // 3) Return successful ApiResponse
            return new ApiResponse<>(
                    true, 
                    "Transcript created/updated successfully", 
                    savedTranscript
            );

        } catch (Exception e) {
            // In case of an error (e.g. date parse error)
            return new ApiResponse<>(
                    false, 
                    "Failed to create/update transcript: " + e.getMessage(), 
                    null
            );
        }
    }

    /**
     * Retrieves the transcript by primary key.
     *
     * @param transcriptId the DB primary key
     * @return an ApiResponse with success status, message, and Transcript data (if found)
     */
    public ApiResponse<Transcript> getTranscript(Long transcriptId) {
        Optional<Transcript> optionalTranscript = transcriptRepository.findById(transcriptId);
        if (optionalTranscript.isPresent()) {
            return new ApiResponse<>(
                    true,
                    "Transcript retrieved successfully",
                    optionalTranscript.get()
            );
        } else {
            return new ApiResponse<>(
                    false,
                    "Transcript not found with ID: " + transcriptId,
                    null
            );
        }
    }

    public ApiResponse<Transcript> patchTranscript(Long transcriptId, TranscriptRequest partialRequest) {
        Optional<Transcript> existingOpt = transcriptRepository.findById(transcriptId);

        if (existingOpt.isEmpty()) {
            return new ApiResponse<>(
                false,
                "Transcript not found with ID: " + transcriptId,
                null
            );
        }
        Transcript existingTranscript = existingOpt.get();
        // Only update fields if they're provided (non-null in TranscriptRequest)
        if (partialRequest.getCreatedTime() != null) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                LocalDateTime dateTime = LocalDateTime.parse(partialRequest.getCreatedTime(), formatter);
                existingTranscript.setCreatedTime(dateTime);
            } catch (Exception e) {
                return new ApiResponse<>(
                    false,
                    "Error parsing createdTime: " + e.getMessage(),
                    null
                );
            }
        }

        if (partialRequest.getPatientName() != null) {
            existingTranscript.setName(partialRequest.getPatientName());
        }
        if (partialRequest.getPatientId() != null) {
            existingTranscript.setPatientId(partialRequest.getPatientId());
        }
        if (partialRequest.getBody() != null) {
            existingTranscript.setBody(partialRequest.getBody());
        }
        // Save the updated Transcript
        Transcript saved = transcriptRepository.save(existingTranscript);

        return new ApiResponse<>(
            true,
            "Transcript partially updated successfully",
            saved
        );
    }
}