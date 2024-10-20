package com.backend5620.service;

import com.backend5620.mapper.HealthReportMapper;
import com.backend5620.object.HealthData;
import com.backend5620.object.HealthReport;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public interface AIAgentService {

    void generateAndSaveHealthReport(int userId);
    String generateReport(String healthData);


}
