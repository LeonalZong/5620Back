package com.backend5620.service;

import com.backend5620.object.HealthReport;
import java.util.List;

public interface HealthReportService {
    HealthReport getHealthReportByUserId(int userId);
    void generateAndSaveHealthReport(int userID);
    List<HealthReport> getAllHealthReportsByUserId(int userId);
    void insertHealthReport(HealthReport healthReport);
    void deleteReport(int reportId, int userId);
}