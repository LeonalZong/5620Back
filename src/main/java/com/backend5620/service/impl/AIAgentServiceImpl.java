package com.backend5620.service.impl;

import com.backend5620.mapper.HealthReportMapper;
import com.backend5620.object.DietSportPreference;
import com.backend5620.object.HealthData;
import com.backend5620.object.HealthReport;
import com.backend5620.service.AIAgentService;
import com.backend5620.service.DietSportPreferenceService;
import com.backend5620.service.HealthDataService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AIAgentServiceImpl implements AIAgentService {

    private static final Logger logger = LoggerFactory.getLogger(AIAgentServiceImpl.class);

    @Autowired
    private HealthDataService healthDataService;

    @Autowired
    private HealthReportMapper healthReportMapper;

    @Autowired
    private DietSportPreferenceService dietSportPreferenceService;

    @Value("${openai.apiKey}")
    private String apiKey;

    @Value("${openai.apiUrl}")
    private String openAiUrl;

    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void generateAndSaveHealthReport(int userId) {
        HealthData healthData = healthDataService.getLatestHealthDataByUserId(userId); // 确保获取的是最新数据
        DietSportPreference dietSportPreference = dietSportPreferenceService.getDietSportPreferenceByUserId(userId);

        if (healthData != null) {
            String prompt = createHealthPrompt(healthData, dietSportPreference); // 生成报告时使用最新的健康数据
            String reportContent = callOpenAIAPI(prompt);
            if (reportContent != null && !reportContent.isEmpty()) {
                HealthReport healthReport = new HealthReport();
                healthReport.setUserId(userId);
                healthReport.setReportContent(reportContent);
                healthReportMapper.insertHealthReport(healthReport); // 保存生成的报告
                logger.info("Health report for user {} saved successfully.", userId);
            } else {
                logger.error("Failed to generate report for user {}: report content is null or empty.", userId);
            }
        } else {
            logger.error("No health data found for user {}", userId);
        }
    }

    @Override
    public String generateReport(String healthData) {
        String prompt = createHealthPromptFromString(healthData);
        return callOpenAIAPI(prompt);
    }

    private String createHealthPrompt(HealthData healthData, DietSportPreference dietSportPreference) {
        StringBuilder prompt = new StringBuilder();
        prompt.append("Patient is a ").append("-year-old ")
                .append(", ").append(healthData.getHeight()).append(" cm tall, weighs ").append(healthData.getWeight())
                .append(" kg, has systolic blood pressure of ").append(healthData.getSystolicBloodPressure())
                .append(", and diastolic blood pressure of ").append(healthData.getDiastolicBloodPressure())
                .append(", cholesterol level is ").append(healthData.getCholesterol()).append(" mg/dL, resting heart rate is ")
                .append(healthData.getRestingHeartRate()).append(" bpm. ");

        if (dietSportPreference != null) {
            prompt.append("The patient has the following diet preferences: ").append(dietSportPreference.getDietPreferences())
                    .append(". And the following sport preferences: ").append(dietSportPreference.getSportPreferences()).append(". ");
        }

        prompt.append("Please generate a comprehensive health report with lifestyle and dietary suggestions.");
        return prompt.toString();
    }

    private String createHealthPromptFromString(String healthData) {
        return "Please generate a health report for the following data: " + healthData;
    }

    private String callOpenAIAPI(String prompt) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + apiKey);
        headers.set("Content-Type", "application/json");

        Map<String, Object> message = new HashMap<>();
        message.put("role", "user");
        message.put("content", prompt);

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("model", "gpt-3.5-turbo");
        requestBody.put("messages", List.of(message));
        requestBody.put("max_tokens", 300);
        requestBody.put("temperature", 0.7);

        try {
            String jsonBody = objectMapper.writeValueAsString(requestBody);
            HttpEntity<String> entity = new HttpEntity<>(jsonBody, headers);
            ResponseEntity<String> response = restTemplate.postForEntity(openAiUrl, entity, String.class);

            return response.getBody();
        } catch (Exception e) {
            logger.error("Error calling OpenAI API", e);
            throw new RuntimeException("Failed to call OpenAI API", e);
        }
    }
}