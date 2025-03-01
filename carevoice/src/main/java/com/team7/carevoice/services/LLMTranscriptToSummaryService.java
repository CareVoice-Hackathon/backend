package com.team7.carevoice.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;

import com.team7.carevoice.model.Transcript;
import com.team7.carevoice.repository.TranscriptRepository;
import java.util.Map;
import java.util.HashMap;

@Service
public class LLMTranscriptToSummaryService {

    @Value("${openai.api.key}")
    private String openaiApiKey;

    private final TranscriptRepository transcriptRepository;
    private final RestTemplate restTemplate;

    public LLMTranscriptToSummaryService(TranscriptRepository transcriptRepository) {
        this.transcriptRepository = transcriptRepository;
        this.restTemplate = new RestTemplate();
    }

    public String generateSummary(Long transcriptId) {
        // 1. Get transcript from DB
        Transcript transcript = transcriptRepository.findById(transcriptId)
            .orElseThrow(() -> new RuntimeException("Transcript not found"));

        // 2. Prepare ChatGPT request
        String prompt = "Please summarize this medical transcript in a concise way: " + transcript.getBody();
        
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("model", "gpt-3.5-turbo");
        requestBody.put("messages", new Object[]{
            Map.of(
                "role", "user",
                "content", prompt
            )
        });

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(openaiApiKey);

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(requestBody, headers);

        // 3. Call ChatGPT API
        String url = "https://api.openai.com/v1/chat/completions";
        Map<String, Object> response = restTemplate.postForObject(url, request, Map.class);

        // 4. Extract summary from response
        return ((Map<String, String>) ((Map<String, Object>) ((java.util.List<?>) response.get("choices")).get(0)).get("message")).get("content");
    }
} 