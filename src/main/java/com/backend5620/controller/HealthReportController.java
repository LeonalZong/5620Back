package com.backend5620.controller;


import com.backend5620.object.HealthReport;
import com.backend5620.object.Result;
import com.backend5620.service.HealthReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/health-report")
public class HealthReportController {

    @Autowired
    private HealthReportService healthReportService;

    @GetMapping("/view")
    public Result viewHealthReport(@RequestParam int userId) {
        HealthReport report = healthReportService.getHealthReportByUserId(userId);
        if (report != null) {
            log.info("report successful" + report);
            return Result.success(report);
        } else {
            log.info("report failed" + userId);
            return Result.error("Health report not found for user ID: " + userId);
        }
    }
    @PostMapping("/generate-report")
    public Result generateHealthReport(@RequestParam int userId) {
        try {
            log.info("Generating health report for user ID: {}", userId);
            healthReportService.generateAndSaveHealthReport(userId);
            return Result.success();
        } catch (Exception e) {
            log.error("Error generating health report for user ID: {}", userId, e);
            return Result.error("Internal server error");
        }
    }
}
