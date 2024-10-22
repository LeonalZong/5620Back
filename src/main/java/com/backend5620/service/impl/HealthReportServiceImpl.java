package com.backend5620.service.impl;

import com.backend5620.mapper.HealthReportMapper;
import com.backend5620.object.HealthReport;
import com.backend5620.service.AIAgentService;
import com.backend5620.service.HealthReportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HealthReportServiceImpl implements HealthReportService {
    private final HealthReportMapper healthReportMapper;
    private final AIAgentService aiAgentService;

    public HealthReportServiceImpl(HealthReportMapper healthReportMapper, AIAgentService aiAgentService) {
        this.healthReportMapper = healthReportMapper;
        this.aiAgentService = aiAgentService;
    }

    @Override
    public HealthReport getHealthReportByUserId(int userId) {
        return healthReportMapper.getHealthReportByUserId(userId);
    }

    @Override
    public void generateAndSaveHealthReport(int userId) {
        aiAgentService.generateAndSaveHealthReport(userId);
    }

    @Override
    public List<HealthReport> getAllHealthReportsByUserId(int userId) {
        return healthReportMapper.getAllHealthReportsByUserId(userId);
    }

    @Override
    public void insertHealthReport(HealthReport healthReport) {
        healthReportMapper.insertHealthReport(healthReport);
    }

    @Override
    public void deleteReport(int reportId, int userId) {
        healthReportMapper.deleteReport(reportId, userId);
    }
}