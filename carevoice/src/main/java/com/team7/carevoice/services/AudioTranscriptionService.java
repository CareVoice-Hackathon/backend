package com.team7.carevoice.services;

import com.team7.carevoice.model.Transcript;
import com.team7.carevoice.repository.TranscriptRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.core.io.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;

@Service
public class AudioTranscriptionService {
    private static final Logger logger = LoggerFactory.getLogger(AudioTranscriptionService.class);

    @Value("${deepgram.api.key}")
    private String deepgramApiKey;

    private final ResourceLoader resourceLoader;
    private final TranscriptRepository transcriptRepository;

    public AudioTranscriptionService(ResourceLoader resourceLoader, TranscriptRepository transcriptRepository) {
        this.resourceLoader = resourceLoader;
        this.transcriptRepository = transcriptRepository;
    }

    public Transcript transcribeAudio(String patientName, Long patientId) throws IOException, InterruptedException {
        // Update this line with your actual file name
        Resource audioResource = resourceLoader.getResource("classpath:static/audio/demo_htt_speech_voice.mp3");
        
        // Add error handling
        if (!audioResource.exists()) {
            throw new RuntimeException("Audio file not found. Please check the path: static/audio/your_file.m4a");
        }
        
        byte[] audioContent = Files.readAllBytes(audioResource.getFile().toPath());
        
        // Create HTTP client
        HttpClient client = HttpClient.newHttpClient();
        
        // Prepare request
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.deepgram.com/v1/listen?model=nova-3&smart_format=true&language=en-US"))
                .header("Authorization", "Token " + deepgramApiKey)
                .header("Content-Type", "audio/m4a")
                .POST(HttpRequest.BodyPublishers.ofByteArray(audioContent))
                .build();

        // Send request and get response
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // Log response status
        logger.info("Transcription status code: " + response.statusCode());
        
        // Parse the JSON response
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(response.body());
        String transcriptText = rootNode
            .path("results")
            .path("channels")
            .get(0)
            .path("alternatives")
            .get(0)
            .path("transcript")
            .asText();

        // Create and save transcript
        Transcript transcript = new Transcript();
        transcript.setPatientName(patientName);
        transcript.setPatientId(patientId);
        transcript.setBody(transcriptText);  // Save only the transcript text

        return transcriptRepository.save(transcript);
    }
} 