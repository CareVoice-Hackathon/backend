package com.team7.carevoice.controller;

import com.team7.carevoice.model.Summary;
import com.team7.carevoice.services.SummaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("summary")
public class SummaryController {
    @Autowired
    private SummaryService summaryService;

    @GetMapping("/{summaryId}")
    public Map<String, Object> getSummaryById(@PathVariable Long summaryId){
        Summary summary = summaryService.getSummaryById(summaryId);
        Map<String, Object> response = new HashMap<>();
        response.put("createdTime", summary.getCreatedTime().toString());
        response.put("patientName", summary.getPatient().getName());
        response.put("patientId", summary.getPatient().getId());
        response.put("body", summary.getBody());
        return response;
    }

    @PatchMapping("/{summaryId}")
    public Summary updateSummary(@PathVariable Long summaryId, @RequestParam String body) {
        return summaryService.updateSummary(summaryId, body);
    }

    @PostMapping("/patient/{patientId}")
    public Summary createSummary(@PathVariable Long patientId, @RequestParam String body) {
        return summaryService.createSummary(patientId, body);
    }
}
