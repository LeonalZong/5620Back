package com.backend5620.controller;

import com.backend5620.object.HealthData;
import com.backend5620.object.Result;
import com.backend5620.pojo.Jwtutils;
import com.backend5620.service.EmailService;
import com.backend5620.service.HealthDataService;
import com.backend5620.service.HealthReportService;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/healthdata")
@Slf4j
public class HealthDataController {

    @Autowired
    private HealthDataService healthDataService;

    @Autowired
    private HealthReportService healthReportService;

    @Autowired
    private EmailService emailService;

    @PostMapping("/save")
    public Result saveHealthData(@RequestBody HealthData healthData, @RequestHeader("Authorization") String token) {
        if (token.startsWith("Bearer ")) {
            token = token.substring(7);
        }

        // 解析 JWT，获取用户ID
        Claims claims = Jwtutils.parseJwt(token);
        int userId = (int) claims.get("id");
        healthData.setUserId(userId);

        try {
            log.info("save health data: {}", healthData);
            healthDataService.saveHealthData(healthData);

            // 检测数据是否异常
            if (isHealthDataAbnormal(healthData)) {
                healthReportService.generateAndSaveHealthReport(userId);
                emailService.sendAbnormalDataNotification(userId);
            }
            return Result.success();
        } catch (Exception e) {
            log.error(e.getMessage());
            return Result.error("Failed to save health data: " + e.getMessage());
        }
    }

    @GetMapping("/get/{userId}")
    public Result getHealthDataByUserId(@RequestHeader("Authorization") String token) {
        try {
            // 移除 "Bearer " 前缀（如果有）
            if (token.startsWith("Bearer ")) {
                token = token.substring(7);
            }

            // 解析 JWT，获取用户ID
            Claims claims = Jwtutils.parseJwt(token);
            int userId = (int) claims.get("id");

            log.info("get health data by userId: {}", userId);
            HealthData healthData = healthDataService.getLatestHealthDataByUserId(userId);
            return Result.success(healthData);
        } catch (Exception e) {
            log.error(e.getMessage());
            return Result.error("Failed to retrieve health data: " + e.getMessage());
        }
    }

    @GetMapping("/history")
    public Result getHistoricalHealthData(@RequestHeader("Authorization") String token) {
        try {
            // 移除 "Bearer " 前缀（如果有）
            if (token.startsWith("Bearer ")) {
                token = token.substring(7);
            }

            // 解析 JWT，获取用户ID
            Claims claims = Jwtutils.parseJwt(token);
            int userId = (int) claims.get("id");

            log.info("Getting historical health data for userId: {}", userId);
            List<HealthData> historicalData = healthDataService.getHistoricalHealthData(userId);

            return Result.success(historicalData);
        } catch (Exception e) {
            log.error("Error getting historical health data: ", e);
            return Result.error("Failed to retrieve historical health data: " + e.getMessage());
        }
    }

    // 修改后的异常判断方法，基于 systolicBloodPressure 和 diastolicBloodPressure
    private boolean isHealthDataAbnormal(HealthData healthData) {
        int systolic = healthData.getSystolicBloodPressure();
        int diastolic = healthData.getDiastolicBloodPressure();

        // 检查血压是否异常
        if (systolic > 140 || diastolic > 90) {
            return true;
        }

        // 检查胆固醇是否异常
        if (healthData.getCholesterol() > 200) {
            return true;
        }

        // 检查静息心率是否异常
        if (healthData.getRestingHeartRate() > 100 || healthData.getRestingHeartRate() < 60) {
            return true;
        }

        return false;
    }
}