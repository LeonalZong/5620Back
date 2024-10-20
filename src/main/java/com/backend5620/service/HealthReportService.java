package com.backend5620.service;

import com.backend5620.object.HealthReport;
import org.springframework.stereotype.Service;


public interface HealthReportService {
    HealthReport getHealthReportByUserId(int userId);

    void generateAndSaveHealthReport(int userID);
}
