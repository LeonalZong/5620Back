package com.backend5620.controller;


import com.backend5620.object.HealthReport;
import com.backend5620.object.Result;
import com.backend5620.pojo.Jwtutils;
import com.backend5620.service.HealthReportService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/health-report")
public class HealthReportController {

    @Autowired
    private HealthReportService healthReportService;

    @GetMapping("/view-all")
    public ResponseEntity<List<HealthReport>> viewAllHealthReports(HttpServletRequest request) {
        try {
            // 从请求头获取token
            String token = request.getHeader("Authorization");
            if (token != null && token.startsWith("Bearer ")) {
                token = token.substring(7);
                // 解析token获取用户ID
                Claims claims = Jwtutils.parseJwt(token);
                int userId = (int) claims.get("id");

                log.info("Fetching reports for user ID: " + userId);

                List<HealthReport> reports = healthReportService.getAllHealthReportsByUserId(userId);
                log.info("Found {} reports for user ID: " + reports.size() + userId);

                return ResponseEntity.ok(reports);
            } else {
                log.error("No authorization token found");
                return ResponseEntity.status(401).build();
            }
        } catch (Exception e) {
            log.error("Error fetching reports: {}", e.getMessage());
            return ResponseEntity.status(500).build();
        }
    }
    @PostMapping("/generate-report")
    public Result generateHealthReport(@RequestParam int userId) {
        try {
            log.info("Generating health report for user ID: " + userId);
            healthReportService.generateAndSaveHealthReport(userId);
            return Result.success();
        } catch (Exception e) {
            log.error("Error generating health report for user ID: " + userId, e);
            return Result.error("Internal server error");
        }
    }

    @DeleteMapping("/delete/{reportId}")
    public ResponseEntity<Void> deleteReport(@PathVariable int reportId, HttpServletRequest request) {
        try {
            String token = request.getHeader("Authorization");
            if (token != null && token.startsWith("Bearer ")) {
                token = token.substring(7);
                Claims claims = Jwtutils.parseJwt(token);
                int userId = (int) claims.get("id");

                log.info("Deleting report ID:" + reportId + "for user ID: {}" + userId);
                healthReportService.deleteReport(reportId, userId);
                return ResponseEntity.ok().build();
            } else {
                log.error("No authorization token found");
                return ResponseEntity.status(401).build();
            }
        } catch (Exception e) {
            log.error("Error deleting report: {}", e.getMessage());
            return ResponseEntity.status(500).build();
        }
    }
}
