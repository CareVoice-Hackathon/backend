package com.team7.carevoice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.team7.carevoice.dto.request.TranscriptRequest;
import com.team7.carevoice.dto.response.ApiResponse;
import com.team7.carevoice.model.Transcript;
import com.team7.carevoice.services.TranscriptService;
import com.team7.carevoice.services.AudioToTranscriptionService;
import com.team7.carevoice.services.LLMTranscriptToSummaryService;

@RestController
@RequestMapping("/api/transcript")
public class TranscriptController {

    private static final Logger logger = LoggerFactory.getLogger(CareVoiceUserAuthentication.class);
    private final TranscriptService transcriptService;
    private final AudioToTranscriptionService audioToTranscriptionService;
    private final LLMTranscriptToSummaryService transcriptToSummaryService;

    public TranscriptController(TranscriptService transcriptService, AudioToTranscriptionService audioToTranscriptionService, LLMTranscriptToSummaryService transcriptToSummaryService) {
        this.transcriptService = transcriptService;
        this.audioToTranscriptionService = audioToTranscriptionService;
        this.transcriptToSummaryService = transcriptToSummaryService;
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
            @RequestBody Map<String, String> request) {

        ApiResponse<Transcript> response = transcriptService.patchTranscript(transcriptId, request.get("body"));

        if (response.isSuccess()) {
            return ResponseEntity.ok(response);
        } else {
            // Typically 404 if not found or 400 if parse errors
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @PostMapping("/transcribe")
    public ResponseEntity<ApiResponse<Transcript>> transcribeAudioToFile() throws Exception {
        ApiResponse<Transcript> response = audioToTranscriptionService.transcribeAudio();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{transcriptId}/summary")
    public ResponseEntity<String> getSummary(@PathVariable Long transcriptId) {
        String summary = transcriptToSummaryService.generateSummary(transcriptId);
        return ResponseEntity.ok(summary);
    }
}