package com.backend5620.service.impl;

import com.backend5620.mapper.HealthReportMapper;
import com.backend5620.object.HealthReport;
import com.backend5620.service.AIAgentService;
import com.backend5620.service.HealthReportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HealthReportServiceImpl implements HealthReportService {

    private static final Logger logger = LoggerFactory.getLogger(HealthReportServiceImpl.class);

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
        try {
            // 调用 AIAgentService 生成报告
            logger.info("Starting to generate health report for user ID: {}", userId);
            aiAgentService.generateAndSaveHealthReport(userId);
            logger.info("Health report generated and saved successfully for user ID: {}", userId);
        } catch (Exception e) {
            // 捕获并记录异常
            logger.error("Failed to generate and save health report for user ID: {}", userId, e);
        }
    }
}