package com.team7.carevoice.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.core.io.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.util.Base64;

@Service
public class AudioTranscriptionService {
    private static final Logger logger = LoggerFactory.getLogger(AudioTranscriptionService.class);

    @Value("${deepgram.api.key}")
    private String deepgramApiKey;

    private final ResourceLoader resourceLoader;

    public AudioTranscriptionService(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    public String transcribeAudio() throws IOException, InterruptedException {
        // Load audio file from static resources
        Resource audioResource = resourceLoader.getResource("classpath:static/audio/demo_htt_speech_voice.mp3");
        byte[] audioContent = Files.readAllBytes(audioResource.getFile().toPath());
        
        // Create HTTP client
        HttpClient client = HttpClient.newHttpClient();
        
        // Prepare request
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.deepgram.com/v1/listen?model=nova-3&smart_format=true&language=en-US"))
                .header("Authorization", "Token " + deepgramApiKey)
                .header("Content-Type", "audio/mpeg")
                .POST(HttpRequest.BodyPublishers.ofByteArray(audioContent))
                .build();

        // Send request and get response
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // Log response status
        logger.info("Transcription status code: " + response.statusCode());
        
        // TODO: Parse JSON response to get transcript
        // For now, just returning the raw response
        return response.body();
    }
} 