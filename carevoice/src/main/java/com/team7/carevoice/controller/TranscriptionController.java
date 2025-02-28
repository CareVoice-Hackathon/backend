package com.team7.carevoice.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.team7.carevoice.services.AudioTranscriptionService;

@RestController
@RequestMapping("/api/transcription")
public class TranscriptionController {
    
    private final AudioTranscriptionService transcriptionService;

    public TranscriptionController(AudioTranscriptionService transcriptionService) {
        this.transcriptionService = transcriptionService;
    }

    @PostMapping("/transcribe")
    public String transcribeAudio() throws Exception {
        return transcriptionService.transcribeAudio();
    }
} 