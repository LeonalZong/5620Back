package com.backend5620.service.impl;

import com.backend5620.mapper.HealthReportMapper;
import com.backend5620.object.HealthReport;
import com.backend5620.service.AIAgentService;
import com.backend5620.service.HealthReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HealthReportServiceImpl implements HealthReportService {
    @Autowired
    private HealthReportMapper healthReportMapper;

    @Autowired
    private AIAgentService aiAgentService;

    @Override
    public HealthReport getHealthReportByUserId(int userId) {
        return healthReportMapper.getHealthReportByUserId(userId);
    }

    @Override
    public void generateAndSaveHealthReport(int userId) {
        aiAgentService.generateAndSaveHealthReport(userId);
    }
}
