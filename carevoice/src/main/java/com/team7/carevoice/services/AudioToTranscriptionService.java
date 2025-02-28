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
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class AudioToTranscriptionService {
    private static final Logger logger = LoggerFactory.getLogger(AudioToTranscriptionService.class);

    @Value("${deepgram.api.key}")
    private String deepgramApiKey;

    private final ResourceLoader resourceLoader;
    private static final String OUTPUT_DIR = "src/main/resources/text_output";

    public AudioToTranscriptionService(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    public String transcribeAudio() throws IOException, InterruptedException {
        // Load audio file
        Resource audioResource = resourceLoader.getResource("classpath:audio/demo_htt_speech_voice.mp3");
        
        if (!audioResource.exists()) {
            throw new RuntimeException("Audio file not found at: classpath:audio/demo_htt_speech_voice.mp3");
        }
        
        byte[] audioContent = Files.readAllBytes(audioResource.getFile().toPath());
        
        // Get transcription from Deepgram
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.deepgram.com/v1/listen?model=nova-3&smart_format=true&language=en-US"))
                .header("Authorization", "Token " + deepgramApiKey)
                .header("Content-Type", "audio/mp3")
                .POST(HttpRequest.BodyPublishers.ofByteArray(audioContent))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // Parse response
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

        // Save to file
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        String filename = "transcript_" + timestamp + ".txt";
        Path outputPath = Paths.get(OUTPUT_DIR, filename);
        
        // Create directory if it doesn't exist
        Files.createDirectories(Paths.get(OUTPUT_DIR));
        
        // Write transcript to file
        Files.writeString(outputPath, transcriptText);
        
        return "Transcript saved to: " + outputPath.toString();
    }
} 