package com.team7.carevoice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.team7.carevoice.dto.request.TranscriptRequest;
import com.team7.carevoice.dto.response.ApiResponse;
import com.team7.carevoice.model.Transcript;
import com.team7.carevoice.services.TranscriptService;
import com.team7.carevoice.services.AudioToTranscriptionService;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping("/api/transcript")
public class TranscriptController {

    private static final Logger logger = LoggerFactory.getLogger(CareVoiceUserAuthentication.class);
    private final TranscriptService transcriptService;
    private final AudioToTranscriptionService audioToTranscriptionService;

    public TranscriptController(TranscriptService transcriptService, AudioToTranscriptionService audioToTranscriptionService) {
        this.transcriptService = transcriptService;
        this.audioToTranscriptionService = audioToTranscriptionService;
    }

    /**
     * POST /api/transcript/{transcriptId}
     * Accepts JSON: { createdTime, patientId, patientName, body }
     */
//    @PostMapping("/")
//    public ResponseEntity<ApiResponse<Transcript>> createTranscript(
//            @PathVariable Long transcriptId,
//            @RequestBody TranscriptRequest request) {
//
//        // (1) Call the service
//        ApiResponse<Transcript> response = transcriptService.createTranscript(transcriptId, request);
//
//        // (2) Decide on HTTP status based on success
//        if (response.isSuccess()) {
//            return ResponseEntity.status(HttpStatus.CREATED).body(response);
//        } else {
//            // maybe return 400 or 500, depending on the nature of the error
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
//        }
//    }

    /**
     * GET /api/transcript/{transcriptId}
     */
    @GetMapping("/{transcriptId}")
    public ResponseEntity<ApiResponse<Transcript>> getTranscript(@PathVariable Long transcriptId) {
        ApiResponse<Transcript> response = transcriptService.getTranscript(transcriptId);

        if (response.isSuccess()) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    /**
     * PATCH /api/transcript/{transcriptId}
     * Partially update the specified Transcript.
     * Request body can contain any subset of the fields: createdTime, patientId, patientName, body
     */
    @PatchMapping("/{transcriptId}")
    public ResponseEntity<ApiResponse<Transcript>> patchTranscript(
            @PathVariable Long transcriptId,
            @RequestBody TranscriptRequest partialRequest) {

        ApiResponse<Transcript> response = transcriptService.patchTranscript(transcriptId, partialRequest);

        if (response.isSuccess()) {
            return ResponseEntity.ok(response);
        } else {
            // Typically 404 if not found or 400 if parse errors
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @PostMapping("/transcribe")
    public ResponseEntity<Map<String, String>> transcribeAudioToFile(
            @RequestParam("file") MultipartFile file,
            @RequestParam("patient") String patient) {

        try {
            // Log request details
            logger.info("Received transcription request for patient: {}", patient);
            logger.info("File name: {}", file.getOriginalFilename());
            logger.info("File type: {}", file.getContentType());
            logger.info("File size: {} bytes", file.getSize());

            // Process the transcription
            String transcript = audioToTranscriptionService.transcribeAudio(file);

            // Save transcription to database
            TranscriptRequest transcriptRequest = new TranscriptRequest(

            );
            transcriptService.createTranscript()

            // Return the transcribed text
            return ResponseEntity.ok(Map.of("message", "Transcription successful", "transcript", transcript));

        } catch (Exception e) {
            logger.error("Error processing transcription: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Failed to process transcription", "details", e.getMessage()));
        }
    }
}