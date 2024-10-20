package com.backend5620.controller;

import com.backend5620.service.AIAgentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/health")
public class AIAgentController {


    private final AIAgentService aiAgentService;

    public AIAgentController(AIAgentService aiAgentService) {
        this.aiAgentService = aiAgentService;
    }

    @PostMapping("/generate-report")
    public ResponseEntity<String> generateHealthReport(@RequestBody String healthData) {
        String report = aiAgentService.generateReport(healthData);
        return ResponseEntity.ok(report);
    }
}
